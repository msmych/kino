package uk.matvey.kino.movies

import java.util.UUID

class Movie(
    val id: UUID,
    val title: String,
    val year: Int,
) {

    init {
        require(title.isNotBlank()) { "Title should not be empty" }
        require(year >= 1878) { "Year should be at least 1878 but was $year" }
    }
}
