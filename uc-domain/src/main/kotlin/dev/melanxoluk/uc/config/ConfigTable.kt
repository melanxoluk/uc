package dev.melanxoluk.uc.config

import dev.melanxoluk.uc.configuration.ConfigurationTable
import dev.melanxoluk.uc.objectJsonb
import org.jetbrains.exposed.dao.id.LongIdTable

object ConfigTable : LongIdTable("config") {
    val configurationId = reference("configuration_id", ConfigurationTable)
    val schema = text("schema")
    val content = objectJsonb("payload")
    // todo hashcode
}
