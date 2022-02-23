package dev.fstudio.mc_discord_bot.utils

import dev.fstudio.mc_discord_bot.Build
import dev.fstudio.mc_discord_bot.model.Config
import net.peanuuutz.tomlkt.Toml
import java.io.File

object ConfigManager {

    private val toml = Toml { ignoreUnknownKeys = true }

    var config: Config
        private set

    private var file = File(
        "config${File.separator}",
        "discord-bot.toml"
    )

    init {
        config = readConfig()
        if (config.version != Build.VERSION)
            config = writeConfig(config)
    }

    private fun writeConfig(old: Config): Config {
        val new = old.copy(version = Build.VERSION)
        file.writeText(toml.encodeToString(Config.serializer(), new))
        return new
    }

    private fun readConfig(): Config = if (!file.exists()) {
            writeConfig(Config())
        } else {
            toml.decodeFromString(Config.serializer(), file.readText())
        }
}
