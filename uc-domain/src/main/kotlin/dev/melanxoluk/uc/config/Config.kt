package dev.melanxoluk.uc.config

import com.fasterxml.jackson.databind.node.ObjectNode

data class Config(
    val id: Long,
    val schema: String,
    val content: ObjectNode
)
