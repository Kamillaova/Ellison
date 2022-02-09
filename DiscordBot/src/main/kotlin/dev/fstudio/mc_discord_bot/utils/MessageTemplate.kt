package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import com.jessecorbett.diskord.api.channel.EmbedImage
import dev.fstudio.mc_discord_bot.api.mcapi.ping.ServerPing
import dev.fstudio.mc_discord_bot.api.mcworldstats.MCWorldApi
import dev.fstudio.mc_discord_bot.api.mcworldstats.Player
import dev.fstudio.mc_discord_bot.api.mcworldstats.Stats
import dev.fstudio.mc_discord_bot.utils.MicsUtil.convertToDead
import dev.fstudio.mc_discord_bot.utils.MicsUtil.fixUnderline
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getAllBlocks
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getGroundWalkedDistance
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getRandomColor
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getSwamDistance
import dev.fstudio.mc_discord_bot.utils.MicsUtil.tickToTime
import dev.fstudio.mc_discord_bot.utils.MicsUtil.withSuffix
import mu.KLogger

object MessageTemplate {

    suspend fun onlinePlayers(data: ServerPing, mcStats: MCWorldApi, logger: KLogger): Embed.() -> Unit {

        val playersList = mutableListOf<EmbedField>()

        data.players.sample?.forEach { player ->
            kotlin.runCatching {
                mcStats.getStats(player.name)
            }.onSuccess {
                playersList.add(
                    EmbedField(
                        player.name.fixUnderline(),
                        "$realDaysInDay ${it.minecraftPlayOneMinute.tickToTime()}",
                        true
                    )
                )
            }.onFailure {
                logger.error(it.stackTraceToString())
            }
        }

        return {
            title = onlinePlayersTitle
            description = "$modsCountOnServer ${data.forgeData?.mods?.size}\n" +
                    "$serverVersion ${data.version.name}\n\n"
            fields = playersList
            color = getRandomColor()
            footer = EmbedFooter("$playersCount ${data.players.online} / ${data.players.max}\n" + footerText)
            thumbnail = EmbedImage(data.favicon)
        }
    }

    fun allPlayers(data: List<Player>): Embed.() -> Unit {

        return {
            title = allPlayersTitle
            description =  StringBuilder().also {
                data.forEachIndexed { index, player ->
                    it.append("**${index + 1}. **${player.name.fixUnderline().convertToDead(player.abandoned)}\n")
                }
            }.toString()
            color = getRandomColor()
            footer = EmbedFooter(footerText)
        }
    }

    fun topPlayers(data: List<Player>): Embed.() -> Unit {

        val topTen = mutableListOf<EmbedField>()

        for (i in 0..9) {
            topTen.add(
                EmbedField(
                    "${i + 1}. ${data[i].name.fixUnderline().convertToDead(data[i].abandoned)}",
                    "$realDaysInDay ${data[i].minecraftPlayOneMinute?.tickToTime()}",
                    false
                )
            )
        }

        return {
            title = topPlayersTitle
            fields = topTen
            color = getRandomColor()
            footer = EmbedFooter(footerText)
        }
    }

    fun playerStats(username: String, data: Stats): Embed.() -> Unit {
        return {
            title = "$playerStatsTitle - $username"
            description = statsDescription(data)
            color = getRandomColor()
            footer = EmbedFooter(footerText)
        }
    }

    private fun statsDescription(data: Stats): String {
        return "**$numberOfActions**\n\n" +
                "**$realDaysInDay **${data.minecraftPlayOneMinute.tickToTime()}\n" +
                "**$killsAndDeath **${data.minecraftPlayerKills.withSuffix()} / ${data.minecraftDeaths.withSuffix()}\n" +
                "**$enchantedItems **${data.minecraftEnchantItem.withSuffix()}\n" +
                "**$droppedItems **${data.minecraftDrop.withSuffix()}\n" +
                "**$killedMobs **${data.minecraftMobKills.withSuffix()}\n" +
                "**$jumpCount **${data.minecraftJump.withSuffix()}\n\n" +
                "**$walkedDistance **${getGroundWalkedDistance(data).withSuffix()}\n" +
                "**$swamDistance **${getSwamDistance(data).withSuffix()}\n" +
                "**$flownDistance **${data.minecraftFlyOneCm.withSuffix()}\n\n" +
                "**$totalDistance **${getAllBlocks(data).withSuffix()}"
    }
}