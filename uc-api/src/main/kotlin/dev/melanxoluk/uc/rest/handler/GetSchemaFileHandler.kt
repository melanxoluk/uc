package dev.melanxoluk.uc.rest.handler

import dev.melanxoluk.uc.UcConfiguration
import org.http4k.routing.ResourceLoader
import org.http4k.routing.static

val GetSchemaFileHandler = { config: UcConfiguration ->
    static(ResourceLoader.Directory(config.schemas.schemasDirectory))
}
