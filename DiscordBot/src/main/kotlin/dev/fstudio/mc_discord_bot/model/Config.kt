package dev.fstudio.mc_discord_bot.model

import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.Comment

@Serializable
data class Config(
    val version: Double = 0.0,
    @Comment("WebAPI host & port")
    val webHost: String = "localhost",
    val webPort: Int = 8080,
    @Comment("Minecraft server connection data")
    val connection: Connection = Connection(),
    @Comment("Discord bot setup data")
    val discord: Discord = Discord()
)

@Serializable
data class Connection(
    val serverIp: String = "localhost",
    val serverPort: String = "25565"
)

@Serializable
data class Discord(
    val botToken: String = "",
    @Comment("Currently available language -> [en, fi, ru]")
    val botLocale: String = "en",
    val commandPrefix: String = "!",
    val channelId: String = "",
    val suggestionChannelId: String = "",
    @Comment("Show online count in bot status")
    val discordStatus: Boolean = false,
    @Comment("Server status update time in seconds")
    val statusUpdateTime: Int = 30
)