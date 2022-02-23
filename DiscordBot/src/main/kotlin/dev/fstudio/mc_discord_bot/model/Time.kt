package dev.fstudio.mc_discord_bot.model

import dev.fstudio.mc_discord_bot.day
import dev.fstudio.mc_discord_bot.hour
import dev.fstudio.mc_discord_bot.minute

data class Time(
    val days: Int,
    val hours: Int,
    val minutes: Int
) {
    override fun toString(): String {
        return "${days}$day (${hours}$hour : ${minutes}$minute)"
    }
}