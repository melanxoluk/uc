package dev.melanxoluk.uc.schema

import org.jetbrains.exposed.dao.id.LongIdTable

object SchemaTable : LongIdTable("json_schema") {
    val name = text("name")
    val description = text("description").nullable()

    /**
     * Base URI to access schema content over HTTP requests 
     * /test
     */
    val uri = text("uri")

    /**
     * Relative file system path where files of the schema are located
     */
    val path = text("path")

    /**
     * Version which is used by default
     */
    val actualVersion = text("actual_version")
}
