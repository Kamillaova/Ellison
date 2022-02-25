package dev.fstudio.mc_discord_bot.diskord.event

import com.jessecorbett.diskord.bot.BotBase
import com.jessecorbett.diskord.bot.EventDispatcherWithContext
import dev.fstudio.mc_discord_bot.di.mcApi
import dev.fstudio.mc_discord_bot.utils.config.ConfigManager.config
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun EventDispatcherWithContext.requestPlayerOnlineStatus(botBase: BotBase) {
    onReady {
        CoroutineScope(Dispatchers.IO).launch {
            while (config.discord.discordStatus) {
                kotlin.runCatching {
                    mcApi.getServerPing(
                        config.connection.serverIp,
                        config.connection.serverPort
                    )
                }.onSuccess { data ->
                    botBase.setStatus("${data.players.online} / ${data.players.max}")
                }.onFailure { error ->
                    botBase.setStatus(error.stackTraceToString())
                }
                delay(config.discord.statusUpdateTime.toLong() * 1000)
            }
        }
    }
}
