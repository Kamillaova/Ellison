package dev.fstudio.mc_discord_bot.model

import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.Comment

@Serializable
data class Config(
    @Comment("URL to webAPI module")
    val webAPI: String = "http://localhost:8080/",
    @Comment("Minecraft server connection data")
    val connection: Connection,
    @Comment("Discord bot setup data")
    val discord: Discord
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
    val discordStatus: Boolean = false,
    val statusUpdateTime: Int = 30
)