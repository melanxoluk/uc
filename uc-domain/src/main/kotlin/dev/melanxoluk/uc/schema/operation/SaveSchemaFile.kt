package dev.melanxoluk.uc.schema.operation

import dev.melanxoluk.uc.UcConfiguration
import dev.melanxoluk.uc.schema.Schema
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class SaveSchemaFile(
    private val schema: Schema,
    private val version: String?,
    private val path: String,
    private val content: ByteArray
) {
   
    companion object {
        private val log = LoggerFactory.getLogger(SaveSchemaFile::class.java)    
    }
    
    fun execute(config: UcConfiguration) {
        val targetVersion = version ?: schema.actualVersion
        
        val rootPath = Paths.get(config.schemas.schemasDirectory)
        val targetFilePath = rootPath.resolve(schema.path).resolve(targetVersion).resolve(path)
        
        Files.createDirectories(targetFilePath.parent)
        Files.write(targetFilePath, content)
        
        log.info("schema file saved ${targetFilePath.absolutePathString()}")
    }
}
