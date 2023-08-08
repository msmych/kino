package uk.matvey.kino

import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import mu.KotlinLogging
import org.slf4j.event.Level
import uk.matvey.kino.movies.Movie
import uk.matvey.kino.movies.MovieRepo
import java.util.UUID
import java.util.UUID.randomUUID

private val log = KotlinLogging.logger("Kino App")

fun main() {
    val movieRepo = MovieRepo()

    log.info { "Starting Kino server" }
    embeddedServer(
        factory = Netty,
        port = 8000,
        host = "localhost",
        module = {
            install(CallLogging) {
                level = Level.INFO
                logger = KotlinLogging.logger("Ktor")
            }
            install(ContentNegotiation) {
                json(Json)
            }
            routing {
                get("/healthcheck") {
                    call.respond(OK)
                }
                route("/api") {
                    route("/movies") {
                        post { rq: CreateMovieRequest ->
                            val movie = movieRepo.add(Movie(randomUUID(), rq.title, rq.year))
                            call.respond(Created, """{"id":"${movie.id}"}""")
                        }
                        get("/{id}") {
                            val movie = movieRepo.find(UUID.fromString(call.parameters.getOrFail("id")))
                            movie?.let {
                                call.respond(OK, buildJsonObject {
                                    put("id", it.id.toString())
                                    put("title", it.title)
                                    put("year", it.year)
                                })
                            } ?: call.respond(NotFound)
                        }
                    }
                }
            }
        }
    ).start(wait = true)
}

@Serializable
data class CreateMovieRequest(
    val title: String,
    val year: Int
)
