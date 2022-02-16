package dev.fstudio.mcworldstats.web.routers

import dev.fstudio.mcworldstats.web.dao.Player
import dev.fstudio.mcworldstats.web.dao.Players
import dev.fstudio.mcworldstats.web.dao.UserOnlineStatsTable
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.routeOnlineStats() {
    get {
        transaction {
            SchemaUtils.create(UserOnlineStatsTable)
            UserOnlineStatsTable.insert {
                Players.insert {
                    Player.new {
                        name = "name 1"
                    }
                    Player.new {
                        name = "name 2"
                    }
                    Player.new {
                        name = "name 3"
                    }
                }
            }
        }
    }
}