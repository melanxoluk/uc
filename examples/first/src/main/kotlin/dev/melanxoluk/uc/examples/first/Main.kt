package dev.melanxoluk.uc.examples.first

import dev.melanxoluk.uc.client.UcClient
import dev.melanxoluk.uc.client.UcKafkaClientConfig
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    // todo push config to uc service? YES
    
    /*
    1. prepare & import schemas zip
    2. get or create 'first' configuration
    3. push configuration update
    4. check not applied configuration changes
    5. apply changes & be sure that changes were received by client
    */
    
    val config = UcKafkaClientConfig(
        endpoint = "http://localhost:9094",
        consumerGroup = "first-service",
        pollingMillis = 1000L,
        pattern = "uc.first"
    )
    
    val client = UcClient(config) { configuration ->  
        log.info("configuration fetched $configuration")
    }.also { it.listen() }
    
    // awaiting while not bored
    Thread.currentThread().join()
}

private fun uploadSchema(path: String, rootPath: String): Long {
    
}

private fun getOrCreateFirstConfiguration(): Long {
    
}

private fun uploadUpdate() {
    
}

private fun fetchChanges() {
    
}

private fun approveChanges() {
    
}
