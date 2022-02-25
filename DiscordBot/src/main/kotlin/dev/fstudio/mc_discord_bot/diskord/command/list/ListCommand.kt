package dev.fstudio.mc_discord_bot.diskord.command.list

import com.jessecorbett.diskord.bot.CommandBuilder

fun CommandBuilder.listCommand() {
    listOf("list", "лист", "l", "л").forEach { alias ->
        command(alias) {
            requestPlayerList(it)
        }
    }
}