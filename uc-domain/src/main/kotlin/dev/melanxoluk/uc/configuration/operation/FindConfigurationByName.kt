package dev.melanxoluk.uc.configuration.operation

import dev.melanxoluk.uc.configuration.Configuration
import dev.melanxoluk.uc.configuration.ConfigurationTable
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.transactions.transaction

class FindConfigurationByName(val name: String) {
    fun execute(): Configuration? {
        return transaction {
            ConfigurationTable
                .select(ConfigurationTable.columns)
                .andWhere { ConfigurationTable.name eq this@FindConfigurationByName.name }
                .firstOrNull()
                ?.let(::Configuration)
        }
    }
}
