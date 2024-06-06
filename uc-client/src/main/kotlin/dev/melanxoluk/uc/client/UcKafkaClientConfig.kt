package dev.melanxoluk.uc.client

data class UcKafkaClientConfig(
    /**
     * Kafka endpoint
     */
    val endpoint: String,

    /**
     * Kafka consumer group
     */
    val consumerGroup: String,

    /**
     * How often to poll changes from topics
     */
    val pollingMillis: Long,

    /**
     * Which topics necessary to listen to detect UC configuration changes,
     * by default it would be started with uc.<configuration-name>.<named-version>.<semantic-version>
     */
    val pattern: String
) {

    /**
     * Prepared properties to initialize kafka consumer
     */
    val kafkaProps = mapOf(
        "bootstrap.servers" to endpoint,
        "auto.offset.reset" to "earliest",
        "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
        "value.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
        "group.id" to consumerGroup,
        "security.protocol" to "PLAINTEXT",
        "enable.auto.commit" to "false"
    )
}
