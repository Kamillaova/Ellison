package dev.fstudio.mc_discord_bot.diskord.event.message_create

import com.jessecorbett.diskord.bot.EventDispatcherWithContext
import dev.fstudio.mc_discord_bot.utils.config.ConfigManager.config
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

fun EventDispatcherWithContext.supportChatManagement() {
    onMessageCreate { message ->
        if (message.channelId == config.discord.supportChannelId) {
            if (message.attachments.isNotEmpty()) {
                message.attachments.forEach {
                    if (it.contentType?.contains("text/plain") == true && it.fileName.isLog()) {
                        val response: io.ktor.client.statement.HttpResponse = HttpClient().get(it.url)
                        response.receive<String>().apply {
                            TODO(
                                "Create self API for Pastebin service. " +
                                        "Because KPastebin library not work and don't have logger"
                            )
                        }
                    }
                    println("===\nContentType: ${it.contentType}\nFilename: ${it.fileName}\nURL: ${it.url}")
                }
            }
        }
    }
}

private fun String.isLog(): Boolean {
    return this.endsWith(".txt", true) || this.endsWith(".log", true)
}
