package dev.melanxoluk.uc.configuration.operation

import dev.melanxoluk.str.cebabl
import dev.melanxoluk.uc.configuration.Configuration
import dev.melanxoluk.uc.configuration.ConfigurationTable
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

class CreateConfiguration(
    private val name: String,
    private val description: String?,
    private val actualVersion: String?
) {
    
    fun execute(): Configuration {
        return transaction { 
            // todo add validation
            val id = ConfigurationTable.insertAndGetId { 
                it[name] = this@CreateConfiguration.name
                it[description] = this@CreateConfiguration.description
                it[path] = this@CreateConfiguration.name.cebabl
                it[actualVersion] = this@CreateConfiguration.actualVersion ?: "1.0.0"
            }
            
            GetConfiguration(id.value).execute()
        }
    }
}
