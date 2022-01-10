package dev.fstudio.mc_discord_bot.api.mcworldstats


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("name")
    val name: String,
    @SerialName("minecraftPlayOneMinute")
    val minecraftPlayOneMinute: Int? = 0
)