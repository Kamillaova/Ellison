package dev.fstudio.mcworldstats

import dev.fstudio.mcworldstats.util.CommandArguments
import dev.fstudio.mcworldstats.util.CommandArguments.host
import dev.fstudio.mcworldstats.util.CommandArguments.port
import dev.fstudio.mcworldstats.web.plugins.configureDatabase
import dev.fstudio.mcworldstats.web.plugins.configureKoin
import dev.fstudio.mcworldstats.web.plugins.configureRouting
import dev.fstudio.mcworldstats.web.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

fun main(args: Array<String>) {

    CommandArguments.parseArgs(args)

    embeddedServer(Netty, host = host, port = port) {
        configureSerialization()
        configureDatabase()
        configureRouting()
        configureKoin()
    }.start(wait = true)

}