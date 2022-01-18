package dev.fstudio.mcworldstats.util

import dev.fstudio.mcworldstats.web.models.Config
import dev.fstudio.mcworldstats.web.models.Database
import dev.fstudio.mcworldstats.web.models.Server
import net.peanuuutz.tomlkt.Toml
import java.io.File
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.notExists
import kotlin.io.path.readText
import kotlin.io.path.writeText

object MicsUtil {
    fun getConfiguration(): Config {

        Toml { ignoreUnknownKeys = true }

        val path = "config"
        val file = "$path/web-api.toml"

        if (Path(path).notExists()) File(path).mkdir()

        if (!File(file).exists()) {
            val config = Toml.encodeToString(Config.serializer(), Config(server = Server(), database = Database()))
            Paths.get(File(file).toURI()).writeText(config)
        }
        return Toml.decodeFromString(Config.serializer(), Paths.get(file).readText())
    }
}