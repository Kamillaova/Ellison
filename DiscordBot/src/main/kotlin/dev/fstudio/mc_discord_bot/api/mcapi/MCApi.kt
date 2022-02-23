package dev.fstudio.mc_discord_bot.api.mcapi

import dev.fstudio.mc_discord_bot.api.mcapi.ping.response.ServerPing

interface MCApi {
    suspend fun getServerPing(
        url: String
    ): ServerPing
}