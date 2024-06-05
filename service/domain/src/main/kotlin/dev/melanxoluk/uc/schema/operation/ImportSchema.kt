package dev.melanxoluk.uc.schema.operation

import java.io.InputStream

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
    
    fun execute() {
        
    }
}
