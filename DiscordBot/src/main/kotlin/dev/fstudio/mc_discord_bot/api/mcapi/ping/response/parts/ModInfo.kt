package dev.fstudio.mc_discord_bot.api.mcapi.ping.response.parts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModInfo(
    @SerialName("modList")
    val modList: List<String>,
    @SerialName("type")
    val type: String
)
