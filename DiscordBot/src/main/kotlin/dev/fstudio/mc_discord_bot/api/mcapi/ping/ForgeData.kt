package dev.fstudio.mc_discord_bot.api.mcapi.ping


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgeData(
    @SerialName("channels")
    val channels: List<Channel>,
    @SerialName("fmlNetworkVersion")
    val fmlNetworkVersion: Int,
    @SerialName("mods")
    val mods: List<Mod>,
    @SerialName("truncated")
    val truncated: Boolean
)