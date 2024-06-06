package dev.melanxoluk.uc.configuration

/**
 * Organic unique id of the config in the configuration, could be reference to
 * the other config and to the other binary or non-structured file, difference 
 * provided by the config of the configuration which defines patterns to diff
 * config files from content files
 */
typealias ConfigPath = String

/**
 * Marker interface to note that entity is used as configuration entry
 */
interface Config

/**
 * To emphasize using of the reference to the other config provide
 * reference as object of the field with strong typing
 * 
 * todo: could be same used to access content file of the configuration?
 */
data class ConfigReference(
    val path: ConfigPath,
    val configuration: String,
    val namedRevision: String,
    val version: String
)
