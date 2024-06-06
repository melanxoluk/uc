package dev.melanxoluk.uc.configuration.operation

import dev.melanxoluk.uc.UcConfig
import dev.melanxoluk.uc.configuration.Configuration
import dev.melanxoluk.uc.configuration.ConfigurationVersion
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.PathWalkOption
import kotlin.io.path.walk

@OptIn(ExperimentalPathApi::class)
class GetConfigurationVersion(
    val configuration: Configuration,
    val version: String
) {
    
    fun execute(config: UcConfig): ConfigurationVersion {
        val rootPath = Paths.get(config.configurations.configurationsDirectory)
        val configurationDir = rootPath.resolve(configuration.path).resolve(version)
        val files = configurationDir.walk(PathWalkOption.INCLUDE_DIRECTORIES).map { it.toString() }.toList()
        return ConfigurationVersion(configuration, version, files) 
    }
}
