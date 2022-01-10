package dev.fstudio.mc_discord_bot.api.mcworldstats

import retrofit2.http.GET
import retrofit2.http.Path

interface MCWorldApi {
    @GET("player/stats/{username}")
    suspend fun getStats(
        @Path("username") username: String
    ): Stats

    @GET("allplayers")
    suspend fun getAllPlayers(): List<Player>

    @GET("top")
    suspend fun getTopPlayers(): List<Player>
}