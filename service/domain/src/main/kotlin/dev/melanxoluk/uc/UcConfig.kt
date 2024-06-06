package dev.melanxoluk.uc

/**
 * UC persistence configuration
 */
data class UcConfig(
    val schemas: UcSchemasConfig
) {
    
    data class UcSchemasConfig(

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
}
