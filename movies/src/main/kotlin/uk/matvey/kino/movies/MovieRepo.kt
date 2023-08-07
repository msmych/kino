package uk.matvey.kino.movies

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class MovieRepo {

    private val movies = ConcurrentHashMap<UUID, Movie>()

    fun add(movie: Movie): Movie {
        movies[movie.id] = movie
        return movie
    }

    fun find(id: UUID): Movie? {
        return movies[id]
    }
}
