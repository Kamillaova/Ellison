package dev.fstudio.mc_discord_bot.diskord.command.roles

import com.jessecorbett.diskord.api.channel.MessageEdit
import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import com.jessecorbett.diskord.util.words
import dev.fstudio.mc_discord_bot.utils.config.ConfigManager

suspend fun BotContext.requestRolesList(message: Message) {
    if (message.channelId == ConfigManager.config.discord.rolesChannelId) {
        if (message.words.size == 2 && message.words[1] == "update") {
            channel(message.channelId).editMessage(
                "832663275065573386",
                MessageEdit("", embed = embedRolesMessage())
            )
        } else {
            message.respond {
                title = embedRolesMessage().title
                description = embedRolesMessage().description
                fields = embedRolesMessage().fields
                color = embedRolesMessage().color
                footer = embedRolesMessage().footer
            }
        }

    }
}
