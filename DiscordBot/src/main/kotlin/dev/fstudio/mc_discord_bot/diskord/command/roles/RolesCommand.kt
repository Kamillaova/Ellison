package dev.fstudio.mc_discord_bot.diskord.command.roles

import com.jessecorbett.diskord.bot.CommandBuilder

fun CommandBuilder.rolesCommand() {
    listOf("roles", "роли", "r", "p").forEach { alias ->
        command(alias) {
            requestRolesList(it)
        }
    }
}