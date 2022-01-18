package dev.fstudio.mcworldstats.web.models

import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.Comment

@Serializable
data class Config(
    @Comment("Path to minecraft world")
    val worldPath: String = "/home/minecraft/world",
    @Comment("WebAPI server settings")
    val server: Server,
    @Comment("Database auth data")
    val database: Database
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