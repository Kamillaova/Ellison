package dev.fstudio.mc_discord_bot.api.mcapi.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MotdJson(
    @SerialName("text")
    val text: String
)