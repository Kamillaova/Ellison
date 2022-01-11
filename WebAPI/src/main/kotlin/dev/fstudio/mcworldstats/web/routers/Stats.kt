package dev.fstudio.mcworldstats.web.routers

import dev.fstudio.mcworldstats.web.api.McStatsApi
import dev.fstudio.mcworldstats.web.dao.UserTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.java.KoinJavaComponent.inject

fun Route.routeStat() {

    val service by inject<McStatsApi>(McStatsApi::class.java)

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

        kotlin.runCatching {
            service.getSimplifyStats(uuid[0])
        }.onSuccess {
            call.respond(HttpStatusCode.OK, it.stats.minecraftCustom)
        }.onFailure {
            call.respond(HttpStatusCode.BadGateway, it.localizedMessage)
        }

    }
}