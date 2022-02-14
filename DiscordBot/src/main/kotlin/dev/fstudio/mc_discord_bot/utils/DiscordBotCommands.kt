package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.api.gateway.events.Ready
import com.jessecorbett.diskord.bot.BotBase
import com.jessecorbett.diskord.bot.BotContext
import com.jessecorbett.diskord.util.words
import dev.fstudio.mc_discord_bot.api.mcapi.MCApi
import dev.fstudio.mc_discord_bot.api.mcworldstats.MCWorldApi
import dev.fstudio.mc_discord_bot.utils.DiskordBotManager.LOGGER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class DiscordBotCommands(
    private val channelId: String,
    private val serverIp: String,
    private val serverPort: String,
    private val status: Boolean,
    private val statusUpdateTime: Int
) {

    val pingUrl = "https://eu.mc-api.net/v3"

    private val mcApi by inject<MCApi>(MCApi::class.java)
    private val mcStats by inject<MCWorldApi>(MCWorldApi::class.java)

    fun requestStatus(client: BotBase): suspend (Ready) -> Unit {
        return {
            CoroutineScope(Dispatchers.IO).launch {
                while (status) {
                    kotlin.runCatching {
                        mcApi.getServerPing("$pingUrl/server/ping/${serverIp}:${serverPort}")
                    }.onSuccess { data ->
                        client.setStatus("${data.players.online} / ${data.players.max}")

                        data.players.sample?.forEach {
                            LOGGER.info("Online status: Player: ${it.name}")
                        }
                    }.onFailure { error ->
                        LOGGER.error("Online status: ${error.stackTraceToString()}")
                    }
                    delay(statusUpdateTime.toLong() * 1000)
                }
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
}