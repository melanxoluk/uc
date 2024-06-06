package dev.melanxoluk.uc.configuration

import org.jetbrains.exposed.dao.id.LongIdTable

object ConfigurationTable : LongIdTable("configuration") {
    val name = text("name")
    val description = text("description").nullable()
    val path = text("path")
}
