package dev.melanxoluk.uc.configuration.operation

import dev.melanxoluk.uc.UcConfig
import dev.melanxoluk.uc.configuration.Configuration
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class SaveConfigurationFile(
    private val configuration: Configuration,
    private val version: String?,
    private val path: String,
    private val content: ByteArray
) {

    companion object {
        private val log = LoggerFactory.getLogger(SaveConfigurationFile::class.java)
    }

    fun execute(config: UcConfig) {
        val targetVersion = version ?: configuration.actualVersion

        val rootPath = Paths.get(config.configurations.configurationsDirectory)
        val targetFilePath = rootPath.resolve(configuration.path).resolve(targetVersion).resolve(path)

        Files.createDirectories(targetFilePath.parent)
        Files.write(targetFilePath, content)

        log.info("configuration file saved ${targetFilePath.absolutePathString()}")
    }
}
