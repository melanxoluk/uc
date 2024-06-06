package dev.melanxoluk.uc.configuration

import org.jetbrains.exposed.sql.ResultRow

data class Configuration(
    val id: Long,
    val name: String,
    val description: String?,
    val path: String,
    val actualVersion: String
) {
    constructor(row: ResultRow) : this(
        id = row[ConfigurationTable.id].value,
        name = row[ConfigurationTable.name],
        description = row[ConfigurationTable.description],
        path = row[ConfigurationTable.path],
        actualVersion = row[ConfigurationTable.actualVersion],
    )
}
