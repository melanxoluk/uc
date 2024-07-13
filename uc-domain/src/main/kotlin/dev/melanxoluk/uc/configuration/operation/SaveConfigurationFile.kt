package dev.melanxoluk.uc.configuration.operation

import com.github.difflib.DiffUtils
import com.github.difflib.patch.Patch
import dev.melanxoluk.uc.UcConfiguration
import dev.melanxoluk.uc.configuration.Configuration
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class SaveConfigurationFile(
    private val configuration: Configuration,
    private val version: String?,
    private val path: String,
    private val content: String
) {

    companion object {
        private val log = LoggerFactory.getLogger(SaveConfigurationFile::class.java)
    }

    fun execute(config: UcConfiguration): Patch<String> {
        val targetVersion = version ?: configuration.actualVersion

        val rootPath = Paths.get(config.configurations.configurationsDirectory)
        val targetFilePath = rootPath.resolve(configuration.path).resolve(targetVersion).resolve(path)

        val actualContent = 
            runCatching { Files.readAllLines(targetFilePath) }
            .getOrElse { emptyList() }
        
        Files.createDirectories(targetFilePath.parent)
        Files.writeString(targetFilePath, content)
        log.info("configuration file saved ${targetFilePath.absolutePathString()}")
        
        return DiffUtils.diff(actualContent, content.lines())
    }
}
