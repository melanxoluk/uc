package dev.melanxoluk.uc.schema.operation

import dev.melanxoluk.uc.schema.Schema
import dev.melanxoluk.uc.schema.SchemaTable
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.transactions.transaction

class FindSchemaByName(private val name: String) {
    fun execute(): Schema? {
        return transaction {
            SchemaTable
                .select(SchemaTable.columns)
                .andWhere { SchemaTable.name eq this@FindSchemaByName.name }
                .firstOrNull()
                ?.let(::Schema)
        }
    }
}
