package dev.melanxoluk.uc.configuration

data class ConfigurationVersion(
    val configuration: Configuration,
    val version: String,
    val files: List<String>
)
