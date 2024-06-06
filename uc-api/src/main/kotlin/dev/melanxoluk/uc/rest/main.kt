package dev.melanxoluk.uc.rest

import com.fasterxml.jackson.databind.ObjectMapper
import dev.melanxoluk.uc.UcConfig
import dev.melanxoluk.uc.rest.handler.CreateConfigurationHandler
import dev.melanxoluk.uc.rest.handler.GetSchemaFileHandler
import dev.melanxoluk.uc.rest.handler.ImportSchemaHandler
import org.http4k.core.Method
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

fun main() {
    val mapper = ObjectMapper()

    val config = UcConfig(
        schemas = UcConfig.UcSchemasConfig(
            schemasDirectory = "schemas",
            acceptableFileExtensions = listOf("json", "yaml", "yml"),
            importArchiveMaxSize = 50 * 1024 * 1024,
            archiveFileMaxSize = 1 * 1024 * 1024
        )
    )
    
    // GOAL
    // ---
    // make universal administration console to edit configuration entries in UI
    // 1) create configuration
    //      1.1) create configuration select? schema, schemaless configuration???? YES
    // 2) import actual configuration files
    // 3) fetch configuration info (name, actual version, files hierarchy)
    // 4) fetch config files
    // 5) save config file change
    // 6) delete config file
    // 
    // GUI
    // ---
    // 1) render configuration page
    // 2) render config file content. Without schema - just file. With schema - auto ui
    // 3) 

    // todo: 
    //  add extra validation for path /schemas/{schema-name}/{schema-version}/{schema-filename}
    //  add support for path /schemas/{schema-name}/{schema-filename} which resolves to actual schema version
    //  add support to miss file extension in uri
    val app = routes(
        "/schemas" bind routes(
            Method.GET bind GetSchemaFileHandler(config),
            "/import" bind Method.POST to ImportSchemaHandler(mapper)
        ),
        "/configurations" bind routes(
            Method.POST bind CreateConfigurationHandler()
        )
    )

    app.asServer(Undertow(19000)).start()
}
