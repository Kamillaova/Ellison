package dev.fstudio.nativemobileclient.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("name")
    val name: String,
    @SerialName("minecraftPlayOneMinute")
    val minecraftPlayOneMinute: Int? = 0
)