package dev.fstudio.mc_discord_bot.api.mcapi.ping


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Channel(
    @SerialName("required")
    val required: Boolean,
    @SerialName("res")
    val res: String,
    @SerialName("version")
    val version: String
)