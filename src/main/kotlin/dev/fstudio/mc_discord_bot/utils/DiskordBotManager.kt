package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import com.jessecorbett.diskord.api.channel.EmbedImage
import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.bot.events
import com.jessecorbett.diskord.util.Colors
import dev.fstudio.mc_discord_bot.api.mcapi.MCApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import org.koin.java.KoinJavaComponent.inject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class DiskordBotManager {

    private val token = "OTIzMjgzMTk4MjUzODkxNjM0.YcNwfA.eeQwQokHGq4Uc_7q5ln2CPERsmA"
    private val service by inject<MCApi>(MCApi::class.java)
    private val random = Random(System.currentTimeMillis())

    suspend fun setupBot(
        channelId: String,
        ip: String = "localhost",
        port: String = "25565",
        status: Boolean = false
    ) {
        coroutineScope {
            launch(newSingleThreadContext(channelId)) {
                bot(token) {
                    events {
                        onReady {
                            while (status) {
                                kotlin.runCatching {
                                    service.getServerStatus(ip, port)
                                }.onSuccess { status ->
                                    logger.info("Online status: Success")
                                    setStatus("${status.players.sample.size} / ${status.players.max}")
                                    logger.info("Online status: ${status.players.sample.size} from ${status.players.max} is online")
                                    status.players.sample.forEach {
                                        logger.info("Online status: Player: ${it.name}")
                                    }
                                }.onFailure {
                                    logger.info("Online status: Failure")
                                    logger.error("Online status: ${it.localizedMessage}")
                                }
                                logger.info("IN IO")
                                delay(30_000)
                            }

                            logger.info("OUT IO")
                        }
                    }

                    logger.info("???? $channelId")
                    classicCommands("!") {
                        listOf("online", "онлайн", "o", "о").forEach { alias ->
                            command(alias) { c ->
                                logger.info("Online request: CMD IN $channelId")
                                if (c.channelId == channelId) {
                                    kotlin.runCatching {
                                        service.getServerStatus(ip, port)
                                    }.onSuccess {

                                        logger.info("Online request: Success")
                                        val playersList = mutableListOf<EmbedField>()
                                        val timeCode = LocalDateTime.now()
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

                                        logger.info("Online request: Currently online")
                                        it.players.sample.forEach { player ->
                                            playersList.add(EmbedField(player.name, player.id, true))
                                            logger.info("Online request: ${player.name}")
                                        }
                                        logger.info("Online request: Total players count: ${it.players.sample.size}")

                                        c.respond {
                                            title = "Онлайн сервера"
                                            description =
                                                "Описалду я потом реализую, ибо нехуй делать статичный текст тогда, когда есть желание сделать первую фичу"
                                            fields = playersList
                                            color = Colors.rgb(
                                                random.nextInt(0, 255),
                                                random.nextInt(0, 255),
                                                random.nextInt(0, 255)
                                            )
                                            footer = EmbedFooter(
                                                "Players: ${it.players.sample.size} / ${it.players.max}\nTime code: $timeCode"
                                            )
                                            logger.info("Online request: Message responded")
                                        }
                                    }.onFailure {
                                        c.respond {
                                            image = EmbedImage("https://mcapi.us/server/image?ip=$ip&port=$port")
                                        }
                                        logger.error("Online request: Failure")
                                        logger.error("Online request: ${it.localizedMessage}")
                                    }
                                }
                                logger.error("Online request: OUT $channelId")

                            }
                        }
                    }
                }
            }
        }
    }
}