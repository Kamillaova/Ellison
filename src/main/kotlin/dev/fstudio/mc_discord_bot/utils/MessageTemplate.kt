package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import com.jessecorbett.diskord.api.channel.EmbedImage
import dev.fstudio.mc_discord_bot.api.mcapi.ping.ServerPing
import dev.fstudio.mc_discord_bot.api.mcworldstats.MCWorldApi
import dev.fstudio.mc_discord_bot.api.mcworldstats.Player
import dev.fstudio.mc_discord_bot.api.mcworldstats.Stats
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getAllBlocks
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getGroundWalkedDistance
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getRandomColor
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getSwamDistance
import dev.fstudio.mc_discord_bot.utils.MicsUtil.tickToTime
import dev.fstudio.mc_discord_bot.utils.MicsUtil.withSuffix
import mu.KLogger

object MessageTemplate {

    val footerText = "Бот создан при поддержки дискорд сообшества Rivaviva | Автор Syorito Hatsuki"

    suspend fun onlinePlayers(data: ServerPing, mcStats: MCWorldApi, logger: KLogger): Embed.() -> Unit {

        val playersList = mutableListOf<EmbedField>()

        data.players.sample?.forEach { player ->
            kotlin.runCatching {
                mcStats.getStats(player.name)
            }.onSuccess {
                playersList.add(EmbedField(player.name, "${it.minecraftPlayOneMinute.tickToTime()}", true))
            }.onFailure {
                logger.error(it.stackTraceToString())
            }
        }

        return {
            title = "Онлайн сервера"
            description = "Модов на сервере ${data.forgeData?.mods?.size}\n" +
                    "Версия сервера: ${data.version.name}\n\n"
            fields = playersList
            color = getRandomColor()
            footer = EmbedFooter("Всего игроков: ${data.players.online} / ${data.players.max}\n" + footerText)
            thumbnail = EmbedImage(data.favicon)
        }
    }

    fun playerStats(username: String, data: Stats): Embed.() -> Unit {
        return {
            title = "Статистика персонажа - $username"
            description = statsDescription(data)
            color = getRandomColor()
            footer = EmbedFooter(footerText)
        }
    }

    fun allPlayers(data: List<Player>): Embed.() -> Unit {
        var list = ""

        data.forEachIndexed { index, player ->
            list += "**${index + 1}. ** ${player.name.replace("_", "＿")}\n"
        }

        return {
            title = "Список игроков сервера"
            description = list
            color = getRandomColor()
            footer = EmbedFooter(footerText)
        }
    }

    fun topPlayers(data: List<Player>): Embed.() -> Unit {

        val topTen = mutableListOf<EmbedField>()

        for (i in 0..9) {
            topTen.add(
                EmbedField(
                    "${i + 1}. ${data[i].name}",
                    data[i].minecraftPlayOneMinute?.tickToTime().toString(),
                    false
                )
            )
        }

        return {
            title = "Топ 10 игроков сервера"
            fields = topTen
            color = getRandomColor()
            footer = EmbedFooter(footerText)
        }
    }

    private fun statsDescription(data: Stats): String {
        return "**Количество действий**\n\n" +
                "**Время в игре: ${data.minecraftPlayOneMinute.tickToTime()}\n" +
                "**Убийств / Смертей: **${data.minecraftPlayerKills.withSuffix()} / ${data.minecraftDeaths.withSuffix()}\n" +
                "**Зачарованых предметов: **${data.minecraftEnchantItem.withSuffix()}\n" +
                "**Выкинутого дропа: **${data.minecraftDrop.withSuffix()}\n" +
                "**Убитых мобов: **${data.minecraftMobKills.withSuffix()}\n" +
                "**Прыжков: **${data.minecraftJump.withSuffix()}\n\n" +
                "**Всего пройдено по земле: **${getGroundWalkedDistance(data).withSuffix()}\n" +
                "**Общее проплытое растояние: **${getSwamDistance(data).withSuffix()}\n" +
                "**Общее расстояние полета: **${data.minecraftFlyOneCm.withSuffix()}\n\n" +
                "**Всего преодалено: **${getAllBlocks(data).withSuffix()}"
    }
}