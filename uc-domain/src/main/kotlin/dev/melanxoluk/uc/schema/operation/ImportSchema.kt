package dev.melanxoluk.uc.schema.operation

import dev.melanxoluk.uc.UcConfiguration
import dev.melanxoluk.uc.UcException
import dev.melanxoluk.uc.schema.Schema
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class ImportSchema(
    private val schemaName: String,
    private val schemaVersion: String?,
    private val archive: InputStream,
    private val mainFile: String,
    private val processErrors: ProcessErrors
) {

    enum class ProcessErrors {
        SAVE,
        FAIL
    }
    
    data class SchemaImported(
        val schema: Schema,
        val messages: List<String>
    )
    
    fun execute(config: UcConfiguration): SchemaImported {
        val messages = mutableListOf<String>()
        val schemaFiles = mutableMapOf<String, ByteArray>()
        val zipIn = ZipInputStream(archive)
        
        lateinit var zipEntry: ZipEntry
        while (zipIn.nextEntry?.also { zipEntry = it } != null) {
            
            // file size entry validation
            if (zipEntry.size > config.schemas.archiveFileMaxSize) {
                throw UcException("Size of the file ${zipEntry.name} larger " +
                    "then max possible ${config.schemas.archiveFileMaxSize}")
            }
            
            // file extension validation
            config.schemas.acceptableFileExtensions.any { 
                zipEntry.name.endsWith(it) 
            } || throw UcException("Wrong file extension ${zipEntry.name}")
            
            // todo
            //  now we're assuming that every file in archive is json schema
            //  and if we have a setting to fail on exception need to validate every of them
            //  with their schema
            run { /*validation*/ }
            
            // continue to process whole zip and prepare content to save on the file system
            val zipFileContent = zipIn.readAllBytes()
            schemaFiles[zipEntry.name] = zipFileContent
        }
        
        // validate that main file presented in archive
        schemaFiles.containsKey(mainFile) || throw UcException("Main file $mainFile not found in archive")
        
        val schema = FindSchemaByName(schemaName).execute() 
            ?: CreateSchema(schemaName, null, schemaVersion).execute()
        
        schemaFiles.forEach { (path, content) -> 
            SaveSchemaFile(schema, schemaVersion, path, content).execute(config)
        }
        
        return SchemaImported(schema, messages)
    }
}
