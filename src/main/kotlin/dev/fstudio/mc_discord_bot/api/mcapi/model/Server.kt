package dev.fstudio.mc_discord_bot.api.mcapi.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Server(
    @SerialName("name")
    val name: String,
    @SerialName("protocol")
    val protocol: Int
)