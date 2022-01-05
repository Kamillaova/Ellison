package dev.fstudio.mc_discord_bot.utils

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required

object CommandArguments {
    private val parser = ArgParser("mcstatus")

    val botToken by parser.option(
        ArgType.String,
        "bot-token",
        "t",
        "Discord bot token"
    ).required()

    val channelId by parser.option(
        ArgType.String,
        "channel-id",
        "c",
        "Discord channel ID for bot"
    ).required()

    val serverIp by parser.option(
        ArgType.String,
        "server-ip",
        "i",
        "Minecraft server IP for pinging"
    ).default("localhost")

    val serverPort by parser.option(
        ArgType.String,
        "server-port",
        "p",
        "Minecraft server port for pinging"
    ).default("25565")

    val discordStatus by parser.option(
        ArgType.Boolean,
        "discord-status",
        "s",
        "Send player count as status"
    ).default(false)

    val statusUpdateTime by parser.option(
        ArgType.Int,
        "status-update-time",
        "u",
        "Status update time in seconds"
    ).default(30)

    fun parseArgs(args: Array<String>) {
        parser.parse(args)
    }
}

