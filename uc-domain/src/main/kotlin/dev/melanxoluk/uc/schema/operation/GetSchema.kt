package dev.melanxoluk.uc.schema.operation

import dev.melanxoluk.uc.UcException
import dev.melanxoluk.uc.schema.Schema
import dev.melanxoluk.uc.schema.SchemaTable
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.transactions.transaction

class GetSchema(private val id: Long) {
    fun execute(): Schema {
        return transaction {
            SchemaTable
                .select(SchemaTable.columns)
                .andWhere { SchemaTable.id eq this@GetSchema.id }
                .firstOrNull()
                ?.let(::Schema)
                ?: throw UcException("Schema not found by id $id")
        }
    }
}
