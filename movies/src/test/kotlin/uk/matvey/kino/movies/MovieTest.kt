package uk.matvey.kino.movies

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import uk.matvey.kino.movies.Movie.Companion.movie
import java.time.Year

class MovieTest {

    @Test
    fun `should validate on instantiation`() {
        assertThatCode { movie("Barbie", Year.of(2023)) }
            .doesNotThrowAnyException()

        assertThatThrownBy { movie("", Year.of(2023)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Title should not be empty")

        assertThatThrownBy { movie("Barbie", Year.of(1789)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Year should be at least 1878 but was 1789")
    }
}
