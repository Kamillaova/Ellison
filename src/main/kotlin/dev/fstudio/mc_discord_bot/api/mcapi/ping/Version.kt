package dev.fstudio.mc_discord_bot.api.mcapi.ping


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Version(
    @SerialName("name")
    val name: String,
    @SerialName("protocol")
    val protocol: Int
)