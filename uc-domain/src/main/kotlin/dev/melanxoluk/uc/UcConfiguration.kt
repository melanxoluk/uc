package dev.melanxoluk.uc

/**
 * UC persistence configuration
 */
data class UcConfiguration(
    val schemas: UcSchemasConfiguration,
    val configurations: UcConfigurations
) {
    
    data class UcSchemasConfiguration(

        /**
         * Directory where schema files are saved
         */
        val schemasDirectory: String,

        /**
         * Which files could be imported: .yaml, .yml, .json, etc.
         */
        val acceptableFileExtensions: List<String>,

        /**
         * Max size of import archives
         */
        val importArchiveMaxSize: Long,

        /**
         * Max file size in archive
         */
        val archiveFileMaxSize: Long,
    )
    
    data class UcConfigurations(
        val configurationsDirectory: String
    )
}
