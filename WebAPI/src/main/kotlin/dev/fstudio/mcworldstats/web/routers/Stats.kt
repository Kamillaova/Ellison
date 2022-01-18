package dev.fstudio.mcworldstats.web.routers

import dev.fstudio.mcworldstats.config
import dev.fstudio.mcworldstats.json
import dev.fstudio.mcworldstats.web.api.model.SimplifyStats
import dev.fstudio.mcworldstats.web.dao.UserTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.json.decodeFromStream
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

fun Route.routeStat() {

    get("player/stats/{username}") {

        val username = call.parameters["username"]

        if (username!!.length < 3) return@get call.respond(
            HttpStatusCode.BadRequest,
            "To short username"
        )

        val uuid = transaction {
            UserTable.select {
                UserTable.name eq username
            }.map {
                it[UserTable.uuid]
            }
        }

        val v = File("${config.worldPath}/stats/${uuid[0]}.json")
        if (v.exists()) {
            val input = json.decodeFromStream(SimplifyStats.serializer(), v.inputStream())
            call.respond(HttpStatusCode.OK, input.stats.minecraftCustom)
        } else call.respond(HttpStatusCode.BadGateway, "Player not founded")

    }

}