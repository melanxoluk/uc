package dev.melanxoluk.uc.configuration

data class Configuration<out T : UConfig>(
    val hash: String,
    val name: String,

    // todo: check configuration in the admin panel
    // val url: String,
    
    val namedRevision: String,
    val version: String,

    /**
     * Content of the configuration presented in configuration files = configs
     */
    val configs: Map<ConfigPath, T>
)
