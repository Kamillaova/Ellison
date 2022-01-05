package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import com.jessecorbett.diskord.api.channel.EmbedImage
import com.jessecorbett.diskord.util.Colors
import dev.fstudio.mc_discord_bot.api.mcapi.ping.ServerPing
import mu.KLogger
import kotlin.random.Random

object MessageTemplate {

    private val random = Random(System.currentTimeMillis())
    lateinit var motd: String

    fun onlinePlayers(data: ServerPing, logger: KLogger): Embed.() -> Unit {

        val playersList = mutableListOf<EmbedField>()

        data.description.extra?.forEach {
            motd += it.text
        }

        if (data.description.extra.isNullOrEmpty()) {
            motd = data.description.text.toString()
        }

        logger.info("Online request: Success")

        logger.info("Online request: Currently online")

        data.players.sample?.forEach { player ->
            playersList.add(EmbedField(player.name, player.id, true))
            logger.info("Online request: ${player.name}")
        }

        logger.info("Online request: Total players count: ${data.players.online}")

        logger.info("Online request: Message responded")

        return {
            title = "Онлайн сервера"
            description =
                "Количество модов на сервере ${data.forgeData?.mods?.size}\n" +
                        "Тип сервера: ${data.version.name}\n" +
                        "\n" +
                        "MOTD: $motd"
            fields = playersList
            color = Colors.rgb(
                random.nextInt(0, 255),
                random.nextInt(0, 255),
                random.nextInt(0, 255)
            )
            footer = EmbedFooter(
                "Players: ${data.players.online} / ${data.players.max}\nTime code: ${data.fetch}"
            )
            thumbnail = EmbedImage(data.favicon)
        }
    }
}