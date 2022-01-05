package dev.fstudio.mc_discord_bot.api.mcapi.ping


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerPing(
    @SerialName("cache")
    val cache: Cache,
    @SerialName("description")
    val description: Description,
    @SerialName("favicon")
    val favicon: String,
    @SerialName("favicon_base64")
    val faviconBase64: String? = null,
    @SerialName("fetch")
    val fetch: String,
    @SerialName("modinfo")
    val modinfo: ModInfo? = null,
    @SerialName("forgeData")
    val forgeData: ForgeData? = null,
    @SerialName("online")
    val online: Boolean,
    @SerialName("players")
    val players: Players,
    @SerialName("source")
    val source: String,
    @SerialName("status")
    val status: Boolean,
    @SerialName("took")
    val took: Float,
    @SerialName("version")
    val version: Version
)