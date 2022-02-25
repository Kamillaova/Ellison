package dev.fstudio.mc_discord_bot.diskord.command.stats

import com.jessecorbett.diskord.bot.CommandBuilder

fun CommandBuilder.statsCommand() {
    listOf("stats", "стата", "s", "с").forEach { alias ->
        command(alias) {
            requestPlayerStats(it)
        }
    }
}
