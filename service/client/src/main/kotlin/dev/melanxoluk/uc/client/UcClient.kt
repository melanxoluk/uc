package dev.melanxoluk.uc.client

import dev.melanxoluk.uc.configuration.UConfig
import dev.melanxoluk.uc.configuration.Configuration

class UcClient {
    suspend inline fun <reified T : UConfig> fetch(
        configuration: String,
        version: String? = null
    ): Configuration<T> {
        /*
        подтягивает из кафки(?) актуальную конфигурацию
         */
    }
}
