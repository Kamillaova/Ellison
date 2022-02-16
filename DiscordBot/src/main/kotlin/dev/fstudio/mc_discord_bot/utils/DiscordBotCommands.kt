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

    private val pingUrl = "https://eu.mc-api.net/v3"
    private val mcApi by inject<MCApi>(MCApi::class.java)
    private val mcStats by inject<MCWorldApi>(MCWorldApi::class.java)

    fun requestStatus(client: BotBase): suspend (Ready) -> Unit {
        LOGGER.info("REQUEST_READY: $status")
        return {
            LOGGER.info("STATUS: $status")
            CoroutineScope(Dispatchers.IO).launch {

                LOGGER.info("Coroutine")
                while (status) {
                    kotlin.runCatching {
                        mcApi.getServerPing("$pingUrl/server/ping/${serverIp}:${serverPort}")
                    }.onSuccess { data ->
                        LOGGER.info("SUCCESS")
                        client.setStatus("${data.players.online} / ${data.players.max}")

                        data.players.sample?.forEach {
                            LOGGER.info("Online status: Player: ${it.name}")
                        }
                    }.onFailure { error ->
                        LOGGER.error("IP -> $serverIp")
                        LOGGER.error("Port -> $serverPort")
                        LOGGER.error("Online status: ${error.stackTraceToString()}")
                        LOGGER.info("FAILURE")
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