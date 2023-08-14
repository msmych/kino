package uk.matvey.kino.routing

import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.util.getOrFail
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import uk.matvey.kino.common.types.IdResponse.Companion.idResponse
import uk.matvey.kino.movies.Movie
import uk.matvey.kino.movies.Movie.Companion.movie
import uk.matvey.kino.movies.MovieService
import uk.matvey.kino.routing.MovieResponse.Companion.response
import java.time.Year
import java.util.UUID

fun Route.movieRouting(movieService: MovieService) {
    route("/movies") {
        post { rq: CreateMovieRequest ->
            val movie = movieService.createMovie(movie(rq.title, rq.year))
            call.respond(Created, idResponse(movie.id))
        }
        route("/{id}") {
            get {
                val movie = movieService.getMovie(call.idPathParam())
                call.respond(OK, movie.response())
            }
            patch { rq: UpdateMovieRequest ->
                movieService.updateMovie(call.idPathParam(), rq.title, rq.year)
                call.respond(NoContent)
            }
        }
    }
}

private fun ApplicationCall.idPathParam(): UUID {
    return UUID.fromString(parameters.getOrFail("id"))
}

@Serializable
data class MovieResponse(
    val id: @Contextual UUID,
    val title: String,
    val year: @Contextual Year
) {

    companion object {

        fun Movie.response() = MovieResponse(id, title, year)
    }
}

@Serializable
data class CreateMovieRequest(
    val title: String,
    val year: @Contextual Year
)

@Serializable
data class UpdateMovieRequest(
    val title: String? = null,
    val year: @Contextual Year? = null
)
