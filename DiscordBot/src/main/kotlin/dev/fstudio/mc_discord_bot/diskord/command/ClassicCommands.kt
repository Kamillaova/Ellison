package dev.fstudio.mc_discord_bot.diskord.command

import com.jessecorbett.diskord.bot.BotBase
import com.jessecorbett.diskord.bot.classicCommands
import dev.fstudio.mc_discord_bot.diskord.command.list.listCommand
import dev.fstudio.mc_discord_bot.diskord.command.online.onlineCommand
import dev.fstudio.mc_discord_bot.diskord.command.roles.rolesCommand
import dev.fstudio.mc_discord_bot.diskord.command.rules.rulesCommand
import dev.fstudio.mc_discord_bot.diskord.command.stats.statsCommand
import dev.fstudio.mc_discord_bot.diskord.command.top.topCommand
import dev.fstudio.mc_discord_bot.utils.config.ConfigManager.config

fun BotBase.loadClassicCommands() {
    classicCommands(config.discord.commandPrefix) {
        listCommand()
        onlineCommand()
        rolesCommand()
        rulesCommand()
        statsCommand()
        topCommand()
    }
}