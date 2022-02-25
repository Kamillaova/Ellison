package dev.fstudio.mc_discord_bot.diskord.command.top

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotContext
import dev.fstudio.mc_discord_bot.di.mcStats
import dev.fstudio.mc_discord_bot.onlineRequestError
import dev.fstudio.mc_discord_bot.utils.config.ConfigManager.config

suspend fun BotContext.requestPlayersTop(message: Message) {
    if (message.channelId == config.discord.privateServerChannelId) {
        kotlin.runCatching {
            mcStats.getTopPlayers()
        }.onSuccess { data ->
            message.respond {
                embedPlayersTopMessage(data)
            }
        }.onFailure {
            message.respond(onlineRequestError)
        }
    }
}
