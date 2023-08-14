package uk.matvey.kino.common.types

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@JvmInline
value class IdResponse(val value: String) {

    companion object {
        fun idResponse(value: String) = IdResponse(value)

        fun idResponse(value: UUID) = idResponse(value.toString())
    }
}
