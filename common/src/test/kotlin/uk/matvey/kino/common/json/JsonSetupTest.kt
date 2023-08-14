package uk.matvey.kino.common.json

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import uk.matvey.kino.common.json.JsonSetup.JSON
import java.time.Year
import java.util.UUID
import java.util.UUID.randomUUID
import java.util.concurrent.ThreadLocalRandom

internal class JsonSetupTest {

    @Test
    fun `should serialize UUID`() {
        val value = randomUUID()

        val serial = SerialUuid(value)

        assertThat(JSON.encodeToString(serial))
            .isEqualTo("""{"value":"$value"}""")
    }

    @Test
    fun `should deserialize UUID`() {
        val value = randomUUID()

        val json = """{"value":"$value"}"""

        val result = JSON.decodeFromString<SerialUuid>(json)

        assertThat(result.value).isEqualTo(value)
    }

    @Test
    fun `should serialize Year`() {
        val value = randomYear()

        val serial = SerialYear(value)

        assertThat(JSON.encodeToString(serial))
            .isEqualTo("""{"value":$value}""")
    }

    @Test
    fun `should deserialize Year`() {
        val value = randomYear()

        val json = """{"value":$value}"""

        val result = JSON.decodeFromString<SerialYear>(json)

        assertThat(result.value).isEqualTo(value)
    }

    @Serializable
    private data class SerialUuid(val value: @Contextual UUID)

    @Serializable
    private data class SerialYear(val value: @Contextual Year)

    companion object {
        private fun randomYear() = Year.of(ThreadLocalRandom.current().nextInt(1970, 2030))
    }
}
