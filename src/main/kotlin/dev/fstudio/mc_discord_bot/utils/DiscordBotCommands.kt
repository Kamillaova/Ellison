package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.common.Message
import com.jessecorbett.diskord.api.gateway.events.Ready
import com.jessecorbett.diskord.bot.BotBase
import com.jessecorbett.diskord.bot.BotContext
import dev.fstudio.mc_discord_bot.api.mcapi.MCApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KLogger
import org.koin.java.KoinJavaComponent

class DiscordBotCommands(
    private val logger: KLogger,
    private val channelId: String,
    private val serverIp: String,
    private val serverPort: String,
    private val status: Boolean,
    private val statusUpdateTime: Int
) {

    private val service by KoinJavaComponent.inject<MCApi>(MCApi::class.java)

    fun requestStatus(client: BotBase): suspend (Ready) -> Unit {
        return {
            CoroutineScope(Dispatchers.IO).launch {
                while (status) {
                    kotlin.runCatching {
                        service.getServerPing(serverIp, serverPort)
                    }.onSuccess { data ->
                        client.setStatus("${data.players.online} / ${data.players.max}")

                        data.players.sample?.forEach {
                            logger.info("Online status: Player: ${it.name}")
                        }
                    }.onFailure {
                        logger.error("Online status: ${it.localizedMessage}")
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
                    service.getServerPing(serverIp, serverPort)
                }.onSuccess { data ->
                    it.respond(block = MessageTemplate.onlinePlayers(data, logger))
                }.onFailure { error ->
                    it.respond(error.localizedMessage)
                    logger.error("Online request: ${error.localizedMessage}")
                }
            }
        }
    }
}