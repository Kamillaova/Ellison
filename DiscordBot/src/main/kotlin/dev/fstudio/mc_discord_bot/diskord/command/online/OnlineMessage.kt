package dev.fstudio.mc_discord_bot.diskord.command.online

import com.jessecorbett.diskord.api.channel.Embed
import com.jessecorbett.diskord.api.channel.EmbedField
import com.jessecorbett.diskord.api.channel.EmbedFooter
import com.jessecorbett.diskord.api.channel.EmbedImage
import dev.fstudio.mc_discord_bot.*
import dev.fstudio.mc_discord_bot.api.mcapi.ping.response.ServerPing
import dev.fstudio.mc_discord_bot.utils.MicsUtil

fun Embed.embedOnlinePlayerListMessage(data: ServerPing, playersList: MutableList<EmbedField>) {
    title = onlinePlayersTitle
    description = "$modsCountOnServer ${data.forgeData?.mods?.size}\n" +
            "$serverVersion ${data.version.name}\n\n"
    fields = playersList
    color = MicsUtil.getRandomColor()
    thumbnail = EmbedImage(data.favicon)
    footer = EmbedFooter("$playersCount ${data.players.online} / ${data.players.max}\n" + footerText)
}