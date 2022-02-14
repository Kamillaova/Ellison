package dev.fstudio.mc_discord_bot.utils

import dev.fstudio.mc_discord_bot.Build
import dev.fstudio.mc_discord_bot.model.Config
import net.peanuuutz.tomlkt.Toml
import java.io.File

object ConfigManager {
    private var file = File(
        "config${File.separator}",
        "discord-bot.toml"
    )

    init {
        Toml { ignoreUnknownKeys = true }

        val config = readConfig()
        if (config.version != Build.VERSION) {
            writeConfig(config)
        }
    }

    private fun writeConfig(oldConfig: Config) {
        file.also {
            val newConfig = Toml.encodeToString(
                Config.serializer(),
                oldConfig.copy(version = Build.VERSION)
            )
            it.writeText(newConfig)
        }
    }

    fun readConfig(): Config {
        if (!file.exists())
            writeConfig(Config())
        return Toml.decodeFromString(Config.serializer(), file.readText())
    }
}
