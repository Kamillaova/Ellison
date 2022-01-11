package dev.fstudio.mcworldstats.web.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimplifyStats(
    @SerialName("stats")
    val stats: Stats
)