package dev.fstudio.mc_discord_bot.api.mcapi.ping


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mod(
    @SerialName("modId")
    val modId: String,
    @SerialName("modmarker")
    val modmarker: String
)