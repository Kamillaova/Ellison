package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.channel.CreateThread
import com.jessecorbett.diskord.api.common.MessageType
import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.bot.events
import dev.fstudio.mc_discord_bot.model.Config
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import mu.KLogger
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DiskordBotManager {

    private lateinit var discordBotCommands: DiscordBotCommands
    lateinit var LOGGER: KLogger

    suspend fun setupBot(config: Config) {
        bot(config.discord.botToken) {

            discordBotCommands = DiscordBotCommands(
                config.discord.channelId,
                config.connection.serverIp,
                config.connection.serverPort,
                config.discord.discordStatus,
                config.discord.statusUpdateTime
            )

            events {
                LOGGER = logger
                onReady {
                    discordBotCommands.requestPlayerOnlineStatus(this@bot)
                }
                onMessageCreate {

                    if (it.channelId == config.discord.suggestionChannelId) {
                        if (it.type == MessageType.REPLY) {
                            return@onMessageCreate channel(it.channelId).deleteMessage(it.id)
                        }

                        val time = DateTimeFormatter.ofPattern("HH꞉mm dd-MM-yyyy")
                            .format(ZonedDateTime.now(ZoneId.of("UTC+3")))
                        coroutineScope {
                            val task = listOf(
                                async {
                                    channel(it.channelId).createThreadFromMessage(
                                        it.id,
                                        CreateThread(time)
                                    )

                                }, async {
                                    it.react("✅")
                                    it.react("❌")
                                })
                            task.awaitAll()
                        }
                    }
                }
            }

            classicCommands(config.discord.commandPrefix) {
                listOf("online", "онлайн", "o", "о").forEach { alias ->
                    command(alias, block = discordBotCommands.requestOnline())
                }

                listOf("top", "топ", "t", "т").forEach { alias ->
                    command(alias, block = discordBotCommands.requestTop())
                }

                listOf("stats", "стата", "s", "с").forEach { alias ->
                    command(alias, block = discordBotCommands.requestStats())
                }

                listOf("list", "лист", "l", "л").forEach { alias ->
                    command(alias, block = discordBotCommands.requestPlayerList())
                }

                listOf("roles", "роли", "r", "p").forEach { alias ->
                    command(alias, block = discordBotCommands.requestRoles())
                }

                listOf("rules", "правила", "r", "п").forEach { alias ->
                    command(alias, block = discordBotCommands.requestRules())
                }
            }
        }
    }
}