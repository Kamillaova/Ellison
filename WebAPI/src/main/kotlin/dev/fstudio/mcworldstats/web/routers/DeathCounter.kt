package dev.fstudio.mcworldstats.web.routers

import dev.fstudio.mcworldstats.util.ConfigManager.config
import dev.fstudio.mcworldstats.web.plugins.json
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*
import kotlinx.serialization.decodeFromString
import java.io.File

fun Route.routeDeathCounter() {
    get("deaths") {
        val deathFile = File(config.minecraftWorlds.deathCounterWorld, "deaths.json")
        if (deathFile.exists()) {
            when (call.request.queryParameters["type"]) {
                "json" -> call.respondFile(deathFile)
                else -> {
                    val data = json.decodeFromString<Map<String, Int>>(String(deathFile.readBytes()))
                    call.respondHtml {
                        head {
                            meta {
                                httpEquiv = "refresh"
                                content = "30"
                            }
                            link {
                                href =
                                    "https://www.dafontfree.net/embed/Zm9yZ290dGVuLWZ1dHVyaXN0LXJlZ3VsYXImZGF0YS84L2YvNDQ3NjIvZm9yZ290dGVuIGZ1dHVyaXN0IHJnLnR0Zg"
                                rel = "stylesheet"
                                type = "text/css"
                            }
                            style {
                                +"h1 { font-family: 'forgotten-futurist-regular', sans-serif; }"
                            }
                        }
                        body {
                            div("reload-area") {
                                data.toSortedMap().forEach {
                                    h1 {
                                        +it.value.toString()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else call.respond(HttpStatusCode.BadGateway, "File not found")
    }
}