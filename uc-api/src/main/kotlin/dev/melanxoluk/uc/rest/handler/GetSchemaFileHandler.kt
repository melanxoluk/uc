package dev.melanxoluk.uc.rest.handler

import dev.melanxoluk.uc.UcConfig
import org.http4k.routing.ResourceLoader
import org.http4k.routing.static

val GetSchemaFileHandler = { config: UcConfig ->
    static(ResourceLoader.Directory(config.schemas.schemasDirectory))
}
