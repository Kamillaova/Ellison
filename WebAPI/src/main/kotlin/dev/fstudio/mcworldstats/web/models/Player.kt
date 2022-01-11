package dev.fstudio.mcworldstats.web.models

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val name: String,
    val uuid: String? = "",
    val minecraftPlayOneMinute: Int? = -1
)