package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.bot.events
import dev.fstudio.mc_discord_bot.model.Config
import mu.KLogger

object DiskordBotManager {

    lateinit var discordBotCommands: DiscordBotCommands
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
                LOGGER.info("EVENTS")
                onReady {
                    LOGGER.info("ON_READY")
                    discordBotCommands.requestStatus(this@bot)
                }
            }

            classicCommands(config.discord.commandPrefix) {
                listOf("online", "онлайн", "o", "о").forEach { alias ->
//                    command(alias, block = discordBotCommands.requestOnline())
                    command(alias, block = discordBotCommands.requestExceptionMessage())
                }

                listOf("top", "топ", "t", "т").forEach { alias ->
//                    command(alias, block = discordBotCommands.requestTop())
                    command(alias, block = discordBotCommands.requestExceptionMessage())
                }

                listOf("stats", "стата", "s", "с").forEach { alias ->
//                    command(alias, block = discordBotCommands.requestStats())
                    command(alias, block = discordBotCommands.requestExceptionMessage())
                }

                listOf("list", "лист", "l", "л").forEach { alias ->
//                    command(alias, block = discordBotCommands.requestPlayerList())
                    command(alias, block = discordBotCommands.requestExceptionMessage())
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