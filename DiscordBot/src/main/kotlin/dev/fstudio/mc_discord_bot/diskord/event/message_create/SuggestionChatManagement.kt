package dev.fstudio.mc_discord_bot.diskord.event.message_create

import com.jessecorbett.diskord.api.channel.CreateThread
import com.jessecorbett.diskord.api.common.MessageType
import com.jessecorbett.diskord.bot.EventDispatcherWithContext
import dev.fstudio.mc_discord_bot.utils.config.ConfigManager
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun EventDispatcherWithContext.suggestionChatManagement() {
    onMessageCreate { message ->
        if (message.channelId == ConfigManager.config.discord.suggestionChannelId) {
            if (message.type == MessageType.REPLY) {
                channel(message.channelId).deleteMessage(message.id)
            }

            val time = DateTimeFormatter.ofPattern("HH꞉mm dd-MM-yyyy")
                .format(ZonedDateTime.now(ZoneId.of("UTC+3")))

            coroutineScope {
                val task = listOf(
                    async {
                        channel(message.channelId).createThreadFromMessage(
                            message.id,
                            CreateThread(time)
                        )
                    },
                    async {
                        message.react("✅")
                        message.react("❌")
                    }
                )
                task.awaitAll()
            }
        }
    }
}