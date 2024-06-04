package dev.melanxoluk.uc.schema.operation

import dev.melanxoluk.uc.schema.Schema
import dev.melanxoluk.uc.schema.SchemaTable
import org.jetbrains.exposed.sql.transactions.transaction

class ListSchemas {
    fun execute(): List<Schema> {
        return transaction { 
            SchemaTable
                .select(SchemaTable.columns)
                .map(::Schema)
        }
    }
}
