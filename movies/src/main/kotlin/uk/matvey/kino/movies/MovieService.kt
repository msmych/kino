package uk.matvey.kino.movies

import java.time.Year
import java.util.UUID

class MovieService(private val movieRepo: MovieRepo) {

    fun createMovie(movie: Movie): Movie {
        return movieRepo.add(movie)
    }

    fun getMovie(id: UUID): Movie {
        return movieRepo.find(id) ?: throw NoSuchElementException("id = $id")
    }

    fun updateMovie(id: UUID, title: String?, year: Year?): Movie {
        val movie = getMovie(id)
        return movieRepo.update(movie.update(title, year))
    }
}
