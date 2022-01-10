package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.bot.events

object DiskordBotManager {

    lateinit var discordBotCommands: DiscordBotCommands

    suspend fun setupBot(
        botToken: String,
        channelId: String,
        serverIp: String = "localhost",
        serverPort: String = "25565",
        status: Boolean = false,
        statusUpdateTime: Int = 30
    ) {
        bot(botToken) {

            discordBotCommands = DiscordBotCommands(logger, channelId, serverIp, serverPort, status, statusUpdateTime)

            events {
                onReady(discordBotCommands.requestStatus(this@bot))
            }

            classicCommands("!") {
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