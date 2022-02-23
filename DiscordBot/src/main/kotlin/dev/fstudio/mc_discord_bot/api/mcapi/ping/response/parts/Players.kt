package dev.fstudio.mc_discord_bot.api.mcapi.ping.response.parts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Players(
    @SerialName("max")
    val max: Int,
    @SerialName("online")
    val online: Int,
    @SerialName("sample")
    val sample: List<Sample>? = null
)