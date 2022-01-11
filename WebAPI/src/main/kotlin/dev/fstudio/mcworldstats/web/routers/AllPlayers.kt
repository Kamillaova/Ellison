package dev.fstudio.mcworldstats.web.routers

import dev.fstudio.mcworldstats.web.dao.UserTable
import dev.fstudio.mcworldstats.web.models.Player
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.routeAllPlayers() {
    get("allplayers") {
        val players = transaction {
            UserTable.selectAll().map {
                Player(it[UserTable.name])
            }
        }

        call.respond(players)
    }
}