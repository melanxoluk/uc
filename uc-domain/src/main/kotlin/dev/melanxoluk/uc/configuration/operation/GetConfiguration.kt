package dev.melanxoluk.uc.configuration.operation

import dev.melanxoluk.uc.UcException
import dev.melanxoluk.uc.configuration.Configuration
import dev.melanxoluk.uc.configuration.ConfigurationTable
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.transactions.transaction

class GetConfiguration(val id: Long) {
    fun execute(): Configuration {
        return transaction { 
            ConfigurationTable
                .select(ConfigurationTable.columns)
                .andWhere { ConfigurationTable.id eq this@GetConfiguration.id }
                .firstOrNull()
                ?.let(::Configuration)
                ?: throw UcException("Not found configuration by id $id")
        }
    }
}
