package dev.melanxoluk.uc.schema

import org.jetbrains.exposed.sql.ResultRow

data class Schema(
    val id: Long,
    val name: String,
    val description: String?,
    val uri: String,
    val path: String
) {
    constructor(row: ResultRow) : this(
        id = row[SchemaTable.id].value,
        name = row[SchemaTable.name],
        description = row[SchemaTable.description],
        uri = row[SchemaTable.uri],
        path = row[SchemaTable.path]
    )
}
