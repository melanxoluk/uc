package dev.melanxoluk.uc.configuration

import org.jetbrains.exposed.dao.id.LongIdTable

object ConfigurationTable : LongIdTable("configuration") {
    val name = text("name").uniqueIndex("configuration_name_index")
    val description = text("description").nullable()
    val path = text("path")
    val actualVersion = text("actual_version")
}
