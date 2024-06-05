package dev.melanxoluk.uc

/**
 * UC persistence configuration
 */
interface UcConfig {
    interface UcSchemasConfig {
        
        /**
         * Directory where schema files are saved
         */
        val schemasDirectory: String

        /**
         * Which files could be imported: .yaml, .yml, .json, etc.
         */
        val acceptableFileExtensions: List<String>
            
        /**
         * Max size of import archives
         */
        val importArchiveMaxSize: Long

        /**
         * Max file size in archive
         */
        val archiveFileMaxSize: Long
    }
    
    val schemas: UcSchemasConfig
}
