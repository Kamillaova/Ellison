package dev.fstudio.mc_discord_bot.api.mcapi

import dev.fstudio.mc_discord_bot.api.mcapi.ping.ServerPing
import retrofit2.http.GET
import retrofit2.http.Url

interface MCApi {
    @GET
    suspend fun getServerPing(
        @Url url: String
    ): ServerPing
}