package dev.melanxoluk.uc.schema.operation

import dev.melanxoluk.uc.UcConfig
import dev.melanxoluk.uc.UcException
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
    
    fun execute(config: UcConfig) {
        val messages = mutableListOf<String>()
        val zipIn = ZipInputStream(archive)
        
        lateinit var zipEntry: ZipEntry
        while (zipIn.nextEntry?.also { zipEntry = it } != null) {
            
            // file size entry validation
            if (zipEntry.size > config.schemas.archiveFileMaxSize) {
                throw UcException("Size of the file ${zipEntry.name} larger then max possible ${config.schemas.archiveFileMaxSize}")
            }
            
            // file extension validation
            config.schemas.acceptableFileExtensions.any { 
                zipEntry.name.endsWith(it) 
            } || throw UcException("Wrong file extension ${zipEntry.name}")
            
            // now we're assuming that every file in archive is json schema
            // and if we have a setting to fail on exception need to validate every of them
            // with their schema
        }
    }
}
