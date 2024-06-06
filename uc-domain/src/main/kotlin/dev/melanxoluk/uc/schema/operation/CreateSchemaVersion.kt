package dev.melanxoluk.uc.schema.operation

class CreateSchemaVersion(
    private val schemaId: Long,
    private val fromVersion: String?,
    private val targetVersion: String
) {
    
    fun execute() {
        val targetFromVersion = fromVersion ?: "default"
        // todo: copy every file from the target directory
    }
}
