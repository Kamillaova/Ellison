package dev.fstudio.mc_discord_bot

import dev.fstudio.mc_discord_bot.di.networkModule
import dev.fstudio.mc_discord_bot.utils.CommandArguments
import dev.fstudio.mc_discord_bot.utils.CommandArguments.botToken
import dev.fstudio.mc_discord_bot.utils.CommandArguments.channelId
import dev.fstudio.mc_discord_bot.utils.CommandArguments.discordStatus
import dev.fstudio.mc_discord_bot.utils.CommandArguments.serverIp
import dev.fstudio.mc_discord_bot.utils.CommandArguments.serverPort
import dev.fstudio.mc_discord_bot.utils.CommandArguments.statusUpdateTime
import dev.fstudio.mc_discord_bot.utils.DiskordBotManager
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.core.context.startKoin

@DelicateCoroutinesApi
suspend fun main(args: Array<String>) {

    startKoin {
        modules(networkModule)
    }

    CommandArguments.parseArgs(args)

    DiskordBotManager.setupBot(
        botToken,
        channelId,
        serverIp,
        serverPort,
        discordStatus,
        statusUpdateTime
    )
}