package dev.fstudio.mc_discord_bot

import dev.fstudio.mc_discord_bot.di.networkModule
import dev.fstudio.mc_discord_bot.utils.DiskordBotManager
import dev.fstudio.mc_discord_bot.utils.MicsUtil.getConfiguration
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.core.context.startKoin
import java.util.*

var bundle: ResourceBundle = ResourceBundle.getBundle("lang", Locale(getConfiguration().discord.botLocale))

@DelicateCoroutinesApi
suspend fun main() {

    startKoin { modules(networkModule) }

    DiskordBotManager.setupBot(getConfiguration())
}