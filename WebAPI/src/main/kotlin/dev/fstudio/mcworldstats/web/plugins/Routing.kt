package dev.fstudio.mcworldstats.web.plugins

import dev.fstudio.mcworldstats.util.ConfigManager.config
import dev.fstudio.mcworldstats.web.routers.routeAllPlayers
import dev.fstudio.mcworldstats.web.routers.routeDeathCounter
import dev.fstudio.mcworldstats.web.routers.routeStat
import dev.fstudio.mcworldstats.web.routers.routeTop
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {
        routeStat()
        routeAllPlayers()
        routeTop()
        static("stats") {
            staticRootFolder = File(config.minecraftWorlds.playerStatsWorld)
            files("stats")
        }
        static("deaths") {
            staticRootFolder = File(config.minecraftWorlds.deathCounterWorld)
            files("deaths")
        }
        routeDeathCounter()
    }
}
