package dev.fstudio.mcworldstats.web.models

import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.Comment

@Serializable
data class Config(
    val version: Double = 0.0,
    @Comment("Path to minecraft worlds")
    val minecraftWorlds: MinecraftWorlds = MinecraftWorlds(),
    @Comment("WebAPI server settings")
    val server: Server = Server(),
    @Comment("Database auth data")
    val database: Database = Database()
)

@Serializable
data class MinecraftWorlds(
    val playerStatsWorld: String = "\$PATH_TO_SERVER_FOLDER/world",
    val deathCounterWorld: String = "\$PATH_TO_SERVER_FOLDER/world"
)

@Serializable
data class Server(
    val host: String = "0.0.0.0",
    val port: Int = 8080,
    val ssl: Boolean = false,
)

@Serializable
data class Database(
    val url: String = "",
    val driver: String = "com.mysql.cj.jdbc.Driver",
    val user: String = "",
    val password:String = ""
)