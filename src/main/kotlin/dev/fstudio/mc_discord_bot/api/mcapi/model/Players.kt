package dev.fstudio.mc_discord_bot.api.mcapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Players(
    @SerialName("max")
    val max: Int,
    @SerialName("now")
    val now: Int,
    @SerialName("sample")
    val sample: List<Sample>
)