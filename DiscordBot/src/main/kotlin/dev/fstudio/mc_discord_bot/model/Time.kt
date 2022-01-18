package dev.fstudio.mc_discord_bot.model

import dev.fstudio.mc_discord_bot.utils.day
import dev.fstudio.mc_discord_bot.utils.hour
import dev.fstudio.mc_discord_bot.utils.minute

data class Time(
    val days: Int,
    val hours: Int,
    val minutes: Int
) {
    override fun toString(): String {
        return "${days}$day (${hours}$hour : ${minutes}$minute)"
    }
}