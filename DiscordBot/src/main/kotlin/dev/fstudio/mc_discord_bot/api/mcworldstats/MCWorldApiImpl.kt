package dev.fstudio.mc_discord_bot.api.mcworldstats

import dev.fstudio.mc_discord_bot.api.mcworldstats.common.response.Player
import dev.fstudio.mc_discord_bot.api.mcworldstats.stats.response.Stats
import io.ktor.client.*
import io.ktor.client.request.*

class MCWorldApiImpl(private val httpClient: HttpClient) : MCWorldApi {
    override suspend fun getStats(username: String): Stats {
        return httpClient.get {
            url {
                encodedPath = "player/stats/$username"
            }
        }
    }

    override suspend fun getAllPlayers(): List<Player> {
        return httpClient.get {
            url {
                encodedPath = "allplayers"
            }
        }
    }

    override suspend fun getTopPlayers(): List<Player> {
        return httpClient.get {
            url {
                encodedPath = "top"
            }
        }
    }
}