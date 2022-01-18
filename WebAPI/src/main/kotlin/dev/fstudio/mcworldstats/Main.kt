package dev.fstudio.mcworldstats

import dev.fstudio.mcworldstats.util.MicsUtil.getConfiguration
import dev.fstudio.mcworldstats.web.plugins.configureDatabase
import dev.fstudio.mcworldstats.web.plugins.configureRouting
import dev.fstudio.mcworldstats.web.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.HttpHeaders.AccessControlAllowOrigin
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }
val config = getConfiguration()

fun main() {

    embeddedServer(Netty, host = config.server.host, port = config.server.port) {
        configureSerialization()
        configureDatabase()
        configureRouting()
        install(CORS) {
            anyHost()
            header(AccessControlAllowOrigin)
        }
    }.start(wait = true)

}