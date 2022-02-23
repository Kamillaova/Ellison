package dev.fstudio.mc_discord_bot.api.mcapi.ping.response.parts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClickEvent(
    @SerialName("action")
    val action: String,
    @SerialName("value")
    val value: String
)