package dev.fstudio.mc_discord_bot

import dev.fstudio.mc_discord_bot.di.networkModule
import dev.fstudio.mc_discord_bot.utils.DiskordBotManager
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.core.context.startKoin

@DelicateCoroutinesApi
suspend fun main() {

    startKoin {
        modules(networkModule)
    }

    val serv1 = DiskordBotManager()
    serv1.setupBot(
        "923947788826468413",
        "play.rivasubs.ru",
//        status = true
    )

    val serv2 = DiskordBotManager()
    serv2.setupBot(
        "923998864757887047",
        "play.hypixel.net",
    )
}