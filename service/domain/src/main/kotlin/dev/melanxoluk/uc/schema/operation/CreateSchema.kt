package dev.melanxoluk.uc.schema.operation

import dev.melanxoluk.str.cebabl
import dev.melanxoluk.uc.UcException
import dev.melanxoluk.uc.schema.Schema
import dev.melanxoluk.uc.schema.SchemaTable
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

class CreateSchema(
    private val name: String,
    private val description: String?
) {

    fun execute(): Schema {
        return transaction {
            val existedSchema =
                SchemaTable
                    .select(SchemaTable.id)
                    .andWhere { SchemaTable.name eq name }
                    .firstOrNull()
                    ?.get(SchemaTable.id)

            if (existedSchema != null)
                throw UcException("Schema with name '$name' already saved")

            val savedId = SchemaTable.insertAndGetId {
                it[name] = this@CreateSchema.name
                it[description] = this@CreateSchema.description
                it[uri] = this@CreateSchema.name.cebabl
                it[path] = this@CreateSchema.name.cebabl
            }

            GetSchema(savedId.value).execute()
        }
    }
}
