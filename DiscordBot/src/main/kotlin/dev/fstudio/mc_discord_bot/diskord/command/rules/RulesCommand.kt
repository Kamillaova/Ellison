package dev.fstudio.mc_discord_bot.diskord.command.rules

import com.jessecorbett.diskord.bot.CommandBuilder

fun CommandBuilder.rulesCommand() {
    listOf("rules", "правила", "r", "п").forEach { alias ->
        command(alias) {
            requestRules(it)
        }
    }
}