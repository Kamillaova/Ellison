package dev.fstudio.mc_discord_bot.api.mcapi.ping.response.parts


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cache(
    @SerialName("insertion_time")
    val insertionTime: String,
    @SerialName("status")
    val status: String,
    @SerialName("ttl")
    val ttl: Int
)