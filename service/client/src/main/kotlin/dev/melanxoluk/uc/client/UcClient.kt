package dev.melanxoluk.uc.client

import dev.melanxoluk.uc.configuration.Configuration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import org.slf4j.LoggerFactory
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.toJavaDuration

class UcClient(
    /**
     * Settings to listen target Kafka topic(s)
     */
    private val config: UcKafkaClientConfig,

    /**
     * Default listener do nothing
     * Provided to make possible detect application readiness
     */
    private val listener: (Configuration<*>) -> Unit = {}
): Runnable {

    private val log = LoggerFactory.getLogger(javaClass)

    private val name = config.consumerGroup
    private val consumer = KafkaConsumer<String, String>(config.kafkaProps).also {
        it.subscribe(config.pattern.toPattern())
    }
    
    fun listen() {
        Thread(this, config.consumerGroup).start()
    }
    
    
    override fun run() {
        while (true) {
            try {
                log.debug("start polling events on listener $name")
                val pollTime = config.pollingMillis.milliseconds.toJavaDuration()
                val records = consumer.poll(pollTime)
                if (records.count() > 0)
                    log.info("polled ${records.count()} records on listener $name")

                processRecords(records).also { commits ->
                    consumer.commitSync(commits)
                    commits.forEach { (topicPartition, offset) ->
                        consumer.seek(topicPartition, offset)
                    }
                }
            } catch (ex: Exception) {
                log.error("exception while listening topics $name", ex)
            }
        }
    }

    private fun processRecords(
        records: Iterable<ConsumerRecord<String, String>>
    ): MutableMap<TopicPartition, OffsetAndMetadata> {
        fun ConsumerRecord<*, *>.str() = "record ${topic()}(${key()})"

        return mutableMapOf<TopicPartition, OffsetAndMetadata>().also { processedRecords ->
            records.groupBy { it.topic() to it.partition() }.forEach { (topicPartition, records) ->
                val (topic, partition) = topicPartition
                val tp = TopicPartition(topic, partition)
                var actualRecord: ConsumerRecord<String, String>? = null

                try {
                    // be sure that events are processed from earliest to oldest
                    records.sortedBy { it.offset() }.forEach { record ->
                        actualRecord = record

                        log.info("start processing ${record.str()}")
                        val (kclass, handler) = handlers[topic] ?: throw IllegalStateException(
                            "Subscription to topic '$topic' without handler"
                        )

                        val atesEvent = validateSchema(record)
                        val event = mapper.treeToValue(atesEvent.payload, kclass.java)
                        handler(event)

                        log.info("${record.str()} successfully processed")

                        // +1 by doc to poll the next records
                        processedRecords[tp] = OffsetAndMetadata(record.offset() + 1)
                    }
                } catch (ex: Exception) {
                    log.error("exception while processing ${actualRecord?.str()} '${ex.message}'")

                    // on the next poll would be received same record
                    processedRecords[tp] = OffsetAndMetadata(actualRecord!!.offset())
                }
            }
        }
    }
    
    private fun handleConfigurationEvent() {
        
    }
}
