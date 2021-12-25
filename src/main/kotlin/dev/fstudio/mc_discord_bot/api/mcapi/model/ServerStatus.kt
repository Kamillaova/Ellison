package dev.fstudio.mc_discord_bot.api.mcapi.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerStatus(
    @SerialName("duration")
    val duration: String,
    @SerialName("error")
    val error: String?,
    @SerialName("favicon")
    val favicon: String,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("motd")
    val motd: String,
    @SerialName("motd_json")
    val motdJson: MotdJson,
    @SerialName("online")
    val online: Boolean,
    @SerialName("players")
    val players: Players,
    @SerialName("server")
    val server: Server,
    @SerialName("status")
    val status: String
)