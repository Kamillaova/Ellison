package dev.fstudio.mc_discord_bot.api.mcapi.ping


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Description(
    @SerialName("extra")
    val extra: List<Extra>? = null,
    @SerialName("text")
    val text: String?
)