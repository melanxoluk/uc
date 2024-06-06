package dev.melanxoluk.uc.configuration.operation

import dev.melanxoluk.uc.UcConfig
import dev.melanxoluk.uc.configuration.Configuration
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class ImportConfiguration(
    private val configurationName: String,
    private val configurationVersion: String?,
    private val archive: InputStream
) {
    
    data class ConfigurationImported(
        val configuration: Configuration,
        val messages: List<String>
    )
    
    fun execute(config: UcConfig): ConfigurationImported {
        // todo save not configuration files to S3

        val configurationFiles = mutableMapOf<String, ByteArray>()
        val zipIn = ZipInputStream(archive)

        lateinit var zipEntry: ZipEntry
        while (zipIn.nextEntry?.also { zipEntry = it } != null) {
            val zipFileContent = zipIn.readAllBytes()
            configurationFiles[zipEntry.name] = zipFileContent
        }

        val configuration = FindConfigurationByName(configurationName).execute()
            ?: CreateConfiguration(configurationName, null, configurationVersion).execute()

        configurationFiles.forEach { (path, content) ->
            SaveConfigurationFile(configuration, configurationVersion, path, content).execute(config)
        }

        return ConfigurationImported(configuration, listOf())
    }
}
