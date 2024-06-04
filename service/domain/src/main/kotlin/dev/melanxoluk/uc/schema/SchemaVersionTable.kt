package dev.melanxoluk.uc.schema

import org.jetbrains.exposed.dao.id.LongIdTable

class SchemaVersionTable : LongIdTable("json_schema_version") {
    val schemaId = reference("json_schema_id", SchemaTable)
    val version = text("version")
}
