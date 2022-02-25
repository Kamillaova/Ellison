package dev.fstudio.mc_discord_bot

import org.koin.java.KoinJavaComponent

// org.koin.core.component.inject ????????
inline fun <reified T> inject() = KoinJavaComponent.inject<T>(T::class.java)
