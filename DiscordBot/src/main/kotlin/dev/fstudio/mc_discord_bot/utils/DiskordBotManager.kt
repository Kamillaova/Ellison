package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.bot.events
import dev.fstudio.mc_discord_bot.model.Config

object DiskordBotManager {

    lateinit var discordBotCommands: DiscordBotCommands

    suspend fun setupBot(config: Config) {
        bot(config.discord.botToken) {

            discordBotCommands = DiscordBotCommands(
                logger,
                config.discord.channelId,
                config.connection.serverIp,
                config.connection.serverPort,
                config.discord.discordStatus,
                config.discord.statusUpdateTime
            )

            events {
                onReady(discordBotCommands.requestStatus(this@bot))
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
            }
        }
    }
}