package dev.fstudio.mc_discord_bot.diskord.command.top

import com.jessecorbett.diskord.bot.CommandBuilder

fun CommandBuilder.topCommand() {
    listOf("top", "топ", "t", "т").forEach { alias ->
        command(alias) {
            requestPlayersTop(it)
        }
    }
}