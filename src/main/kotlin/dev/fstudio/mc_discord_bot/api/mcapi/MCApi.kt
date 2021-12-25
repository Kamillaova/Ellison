package dev.fstudio.mc_discord_bot.api.mcapi

import dev.fstudio.mc_discord_bot.api.mcapi.model.ServerStatus
import retrofit2.http.GET
import retrofit2.http.Query

interface MCApi {
    @GET("/server/status")
    suspend fun getServerStatus(
        @Query("ip") ip: String,
        @Query("port") port: String
    ): ServerStatus
}