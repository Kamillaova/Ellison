package dev.fstudio.mc_discord_bot.diskord.command.list

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import dev.fstudio.mc_discord_bot.di.mcStats
import dev.fstudio.mc_discord_bot.onlineRequestError
import dev.fstudio.mc_discord_bot.utils.config.ConfigManager.config

suspend fun BotContext.requestPlayerList(message: Message) {
    if (message.channelId == config.discord.privateServerChannelId) {
        kotlin.runCatching {
            mcStats.getAllPlayers()
        }.onSuccess { data ->
            message.respond {
                embedPlayerListMessage(data)
            }
        }.onFailure {
            message.respond(onlineRequestError)
        }
    }
}