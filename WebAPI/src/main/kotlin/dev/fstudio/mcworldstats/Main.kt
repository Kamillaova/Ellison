package dev.fstudio.mcworldstats

import dev.fstudio.mcworldstats.util.ConfigManager.config
import dev.fstudio.mcworldstats.web.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, host = config.server.host, port = config.server.port) {
        configureSerialization()
        configureDatabase()
        configureRouting()
        configureCORS()
        configureCachingHeaders()
    }.start(wait = true)
}