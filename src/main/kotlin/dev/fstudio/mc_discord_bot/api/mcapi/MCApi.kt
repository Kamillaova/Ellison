package dev.fstudio.mc_discord_bot.api.mcapi

import dev.fstudio.mc_discord_bot.api.mcapi.ping.ServerPing
import retrofit2.http.GET
import retrofit2.http.Path

interface MCApi {
    @GET("server/ping/{ip}:{port}")
    suspend fun getServerPing(
        @Path("ip") ip: String,
        @Path("port") port: String
    ): ServerPing
}