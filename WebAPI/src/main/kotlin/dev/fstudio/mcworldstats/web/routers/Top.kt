package dev.fstudio.mcworldstats.web.routers

import dev.fstudio.mcworldstats.config
import dev.fstudio.mcworldstats.json
import dev.fstudio.mcworldstats.web.api.model.SimplifyStats
import dev.fstudio.mcworldstats.web.dao.UserTable
import dev.fstudio.mcworldstats.web.models.Player
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.json.decodeFromStream
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

fun Route.routeTop() {

    get("top") {
        val playerList = transaction {
            UserTable.selectAll().map {
                Player(it[UserTable.name], it[UserTable.uuid], abandoned = it[UserTable.abandoned])
            }
        }

        val result = mutableListOf<Player>()

        playerList.forEach {
            val v = File("${config.worldPath}/stats/${it.uuid}.json")
            if (v.exists()) {
                val input = json.decodeFromStream(SimplifyStats.serializer(), v.inputStream())
                result.add(Player(it.name, minecraftPlayOneMinute = input.stats.minecraftCustom.minecraftPlayOneMinute,
                    abandoned = it.abandoned
                ))
            } else {
                result.add(Player(it.name, minecraftPlayOneMinute = 0, abandoned = it.abandoned))
            }
        }

        result.sortByDescending { it.minecraftPlayOneMinute }

        call.respond(result)
    }
}