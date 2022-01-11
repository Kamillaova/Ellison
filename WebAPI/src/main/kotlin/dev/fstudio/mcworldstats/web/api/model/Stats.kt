package dev.fstudio.mcworldstats.web.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("minecraft:custom")
    val minecraftCustom: MinecraftCustom
)