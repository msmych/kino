package uk.matvey.kino.common.json

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

object JsonSetup {

    val JSON = Json {
        serializersModule = SerializersModule {
            contextual(UuidSerializer)
            contextual(YearSerializer)
        }
    }
}
