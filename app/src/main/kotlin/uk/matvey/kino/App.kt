package uk.matvey.kino

import mu.KotlinLogging
import uk.matvey.kino.movies.Movie
import uk.matvey.kino.movies.MovieRepo
import java.util.UUID.randomUUID

private val log = KotlinLogging.logger("Kino App")

fun main() {
    val movieRepo = MovieRepo()

    val movieId = randomUUID()
    movieRepo.add(Movie(movieId, "Barbie", 2023))

    val movie = movieRepo.find(movieId)
    log.info { "Hello, ${movie?.title}!" }
}
