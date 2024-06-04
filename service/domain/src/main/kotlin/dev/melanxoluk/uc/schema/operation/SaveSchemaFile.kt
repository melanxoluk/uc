package dev.melanxoluk.uc.schema.operation

import dev.melanxoluk.uc.UcConfig
import java.nio.file.Files
import java.nio.file.Paths

class SaveSchemaFile(
    private val schemaId: Long,
    private val version: String?,
    private val path: String,
    private val content: String
) {
    
    fun execute(config: UcConfig) {
        val schema = GetSchema(schemaId).execute()
        val targetVersion = version ?: "default"
        val targetFilePath = Paths.get(config.schemasDirectory, schema.path, targetVersion, path)
        Files.writeString(targetFilePath, content)
    }
}
