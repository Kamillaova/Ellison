package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.bot.BotBase
import com.jessecorbett.diskord.bot.BotContext
import com.jessecorbett.diskord.util.words
import dev.fstudio.mc_discord_bot.api.mcapi.MCApi
import dev.fstudio.mc_discord_bot.api.mcworldstats.MCWorldApi
import dev.fstudio.mc_discord_bot.onlineRequestError
import dev.fstudio.mc_discord_bot.statsRequestError
import dev.fstudio.mc_discord_bot.utils.DiskordBotManager.LOGGER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

const val pingUrl = "https://eu.mc-api.net/v3"

class DiscordBotCommands(
    private val channelId: String,
    private val serverIp: String,
    private val serverPort: String,
    private val discordStatus: Boolean,
    private val statusUpdateTime: Int
) {
    private val mcApi by inject<MCApi>(MCApi::class.java)
    private val mcStats by inject<MCWorldApi>(MCWorldApi::class.java)

    fun requestPlayerOnlineStatus(client: BotBase) {
        CoroutineScope(Dispatchers.IO).launch {
            while (discordStatus) {
                kotlin.runCatching {
                    mcApi.getServerPing("$pingUrl/server/ping/${serverIp}:${serverPort}")
                }.onSuccess { data ->
                    client.setStatus("${data.players.online} / ${data.players.max}")
                }.onFailure { error ->
                    client.setStatus(error.stackTraceToString())
                }
                delay(statusUpdateTime.toLong() * 1000)
            }
        }
    }

    fun requestOnline(): suspend BotContext.(Message) -> Unit {
        return {
            if (it.channelId == channelId) {
                kotlin.runCatching {
                    mcApi.getServerPing("$pingUrl/server/ping/${serverIp}:${serverPort}")
                }.onSuccess { data ->
                    it.respond(block = MessageTemplate.onlinePlayers(data, mcStats))
                }.onFailure { error ->
                    it.respond(onlineRequestError)
                    LOGGER.error("Online request: ${error.stackTraceToString()}")
                }
            }
        }
    }

    fun requestStats(): suspend BotContext.(Message) -> Unit {
        return {
            if (it.channelId == channelId) {
                if (it.words.size == 2) {
                    val username = it.words[1]
                    kotlin.runCatching {
                        mcStats.getStats(username)
                    }.onSuccess { data ->
                        it.respond(block = MessageTemplate.playerStats(username, data))
                    }.onFailure { error ->
                        it.respond(onlineRequestError)
                        LOGGER.error("Stats request: ${error.stackTraceToString()}")
                    }
                } else it.respond(statsRequestError)
            }
        }
    }

    fun requestPlayerList(): suspend BotContext.(Message) -> Unit {
        return {
            if (it.channelId == channelId) {
                kotlin.runCatching {
                    mcStats.getAllPlayers()
                }.onSuccess { data ->
                    it.respond(block = MessageTemplate.allPlayers(data))
                }.onFailure { error ->
                    it.respond(onlineRequestError)
                    LOGGER.error("Online status: ${error.stackTraceToString()}")
                }
            }
        }
    }

    fun requestTop(): suspend BotContext.(Message) -> Unit {
        return {
            if (it.channelId == channelId) {
                kotlin.runCatching {
                    mcStats.getTopPlayers()
                }.onSuccess { data ->
                    it.respond(block = MessageTemplate.topPlayers(data))
                }.onFailure { error ->
                    it.respond(onlineRequestError)
                    LOGGER.error("Online status: ${error.stackTraceToString()}")
                }
            }
        }
    }

    fun requestRoles(): suspend BotContext.(Message) -> Unit {
        return {
            listOf(
                "832663275065573386",
                "927604889360687204",
                "923947788826468413",
                "825062492630941718"
            ).forEach { channelsIds ->
                if (it.channelId == channelsIds) {
                    it.respond(block = MessageTemplate.roles())
                }
            }
        }
    }

    fun requestRules(): suspend BotContext.(Message) -> Unit {
        return {
            listOf(
                "814354006977282069",
                "923947788826468413",
                "825062492630941718"
            ).forEach { channelsIds ->
                if (it.channelId == channelsIds) {
                    it.respond(block = MessageTemplate.rules())
                }
            }
        }
    }

    fun requestExceptionMessage(): suspend BotContext.(Message) -> Unit {
        return {
            if (it.channelId == channelId) {
                it.respond(block = MessageTemplate.exception())
            }
        }
    }
}