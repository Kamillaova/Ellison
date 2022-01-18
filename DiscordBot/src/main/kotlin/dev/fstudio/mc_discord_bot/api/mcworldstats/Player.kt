package dev.fstudio.mc_discord_bot.api.mcworldstats


import dev.fstudio.mc_discord_bot.utils.MicsUtil.fixUnderline
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("name")
    val name: String,
    @SerialName("minecraftPlayOneMinute")
    val minecraftPlayOneMinute: Int? = 0,
    @SerialName("abandoned")
    val abandoned: Boolean? = false
) {
    override fun toString(): String {
        return super.toString().fixUnderline()
    }
}