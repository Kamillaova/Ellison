package dev.fstudio.mc_discord_bot.api.mcapi.ping


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Extra(
    @SerialName("bold")
    val bold: Boolean? = false,
    @SerialName("color")
    val color: String? = "",
    @SerialName("italic")
    val italic: Boolean? = false,
    @SerialName("obfuscated")
    val obfuscated: Boolean? = false,
    @SerialName("strikethrough")
    val strikethrough: Boolean? = false,
    @SerialName("text")
    val text: String? = "",
    @SerialName("underlined")
    val underlined: Boolean? = false,
    @SerialName("clickEvent")
    val clickEvent: ClickEvent? = null
)