package dev.fstudio.mc_discord_bot.diskord.command.online

import com.jessecorbett.diskord.bot.CommandBuilder

fun CommandBuilder.onlineCommand() {
    listOf("online", "онлайн", "o", "о").forEach { alias ->
        command(alias) {
            requestOnlinePlayersList(it)
        }
    }
}