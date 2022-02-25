package dev.fstudio.mc_discord_bot

import dev.fstudio.mc_discord_bot.utils.config.ConfigManager.config
import java.util.*
import kotlin.reflect.KProperty

var bundle: ResourceBundle = ResourceBundle.getBundle("lang", Locale(config.discord.botLocale))

class LocaleMessage {
    var value: String? = null

    operator fun getValue(th: Any?, prop: KProperty<*>): String {
        return value ?: bundle.getString(prop.name).apply { value = this }
    }
}

/*   Messages   */
val footerText: String = bundle.getString("embedFooterText")
val onlineRequestError by LocaleMessage()
val statsRequestError by LocaleMessage()

/*   All players   */
val allPlayersTitle by LocaleMessage()

/*   Top players   */
val topPlayersTitle by LocaleMessage()

/*   Online players   */
val onlinePlayersTitle by LocaleMessage()
val modsCountOnServer by LocaleMessage()
val serverVersion by LocaleMessage()
val playersCount by LocaleMessage()

/*   Player stats   */
val realDaysInDay by LocaleMessage()
val playerStatsTitle by LocaleMessage()
val numberOfActions by LocaleMessage()
val killsAndDeath by LocaleMessage()
val enchantedItems by LocaleMessage()
val droppedItems by LocaleMessage()
val killedMobs by LocaleMessage()
val jumpCount by LocaleMessage()
val walkedDistance by LocaleMessage()
val swamDistance by LocaleMessage()
val flownDistance by LocaleMessage()
val totalDistance by LocaleMessage()

/*   Time   */
val day by LocaleMessage()
val hour by LocaleMessage()
val minute by LocaleMessage()
