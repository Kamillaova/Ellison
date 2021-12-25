package dev.fstudio.mc_discord_bot.api.mcapi.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sample(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)