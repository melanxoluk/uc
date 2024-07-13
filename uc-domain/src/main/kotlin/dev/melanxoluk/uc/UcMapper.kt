package dev.melanxoluk.uc

import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.JsonBColumnType

val UcMapper = jacksonObjectMapper()

fun Table.objectJsonb(name: String): Column<ObjectNode> =
    registerColumn(
        name = name, 
        type = JsonBColumnType(
            serialize = { UcMapper.writeValueAsString(it) }, 
            deserialize = { UcMapper.readTree(it) as ObjectNode }
        )
    )
