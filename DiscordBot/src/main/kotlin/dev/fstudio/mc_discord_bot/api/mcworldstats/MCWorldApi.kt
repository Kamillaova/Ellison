package dev.fstudio.mc_discord_bot.api.mcworldstats

import dev.fstudio.mc_discord_bot.api.mcworldstats.common.response.Player
import dev.fstudio.mc_discord_bot.api.mcworldstats.stats.response.Stats

interface MCWorldApi {
    suspend fun getStats(
        username: String
    ): Stats

    suspend fun getAllPlayers(): List<Player>

    suspend fun getTopPlayers(): List<Player>
}