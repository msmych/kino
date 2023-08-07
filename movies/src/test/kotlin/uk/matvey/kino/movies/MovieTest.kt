package uk.matvey.kino.movies

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class MovieTest {

    @Test
    fun `should validate on instantiation`() {
        assertThatCode { Movie(randomUUID(), "Barbie", 2023) }
            .doesNotThrowAnyException()

        assertThatThrownBy { Movie(randomUUID(), "", 2023) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Title should not be empty")

        assertThatThrownBy { Movie(randomUUID(), "Barbie", 1789) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Year should be at least 1878 but was 1789")
    }
}
