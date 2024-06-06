package dev.melanxoluk.uc.rest.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response

class ImportSchemaHandler(
    private val mapper: ObjectMapper,
) : HttpHandler {

    override fun invoke(request: Request): Response {
        TODO("Not yet implemented")
    }
}
