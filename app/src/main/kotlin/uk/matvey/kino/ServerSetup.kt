package uk.matvey.kino

import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import mu.KotlinLogging
import org.slf4j.event.Level
import uk.matvey.kino.common.json.JsonSetup.JSON
import uk.matvey.kino.routing.movieRouting

fun setupServer(services: Services) = embeddedServer(
    factory = Netty,
    port = 8000,
    host = "localhost",
    module = { setupServerModule(services) }
)

private fun Application.setupServerModule(services: Services) {
    install(CallLogging) {
        level = Level.INFO
        logger = KotlinLogging.logger("Ktor")
    }
    install(ContentNegotiation) {
        json(JSON)
    }
    install(StatusPages) {
        exception<NoSuchElementException> { call, cause ->
            call.respond(NotFound, errorJson(cause.message))
        }
    }
    routing {
        get("/healthcheck") {
            call.respond(OK)
        }
        route("/api") {
            movieRouting(services.movieService)
        }
    }
}

private fun errorJson(message: String?) = buildJsonObject {
    put("message", message)
}
