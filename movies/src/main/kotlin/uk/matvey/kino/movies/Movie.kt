package uk.matvey.kino.movies

import java.time.Year
import java.util.UUID
import java.util.UUID.randomUUID

data class Movie(
    val id: UUID,
    val title: String,
    val year: Year,
) {

    init {
        require(title.isNotBlank()) { "Title should not be empty" }
        require(!year.isBefore(FIRST_MOVIE_YEAR)) { "Year should be at least 1878 but was $year" }
    }

    fun update(title: String?, year: Year?): Movie {
        return copy(
            id = this.id,
            title = title ?: this.title,
            year = year ?: this.year
        )
    }

    companion object {
        private val FIRST_MOVIE_YEAR = Year.of(1878)

        fun movie(title: String, year: Year): Movie {
            return Movie(randomUUID(), title, year)
        }
    }
}
