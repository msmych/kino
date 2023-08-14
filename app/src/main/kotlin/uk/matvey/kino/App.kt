package uk.matvey.kino

import mu.KotlinLogging

private val log = KotlinLogging.logger("Kino App")

fun main() {
    val repos = Repos("/hikari.properties")

    val services = Services(repos)

    log.info { "Starting Kino server" }

    setupServer(services).start(wait = true)
}
