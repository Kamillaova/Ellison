package dev.fstudio.mc_discord_bot.api.mcapi

import dev.fstudio.mc_discord_bot.api.mcapi.ping.response.ServerPing
import io.ktor.client.*
import io.ktor.client.request.*

class MCApiImpl(private val httpClient: HttpClient) : MCApi {
    override suspend fun getServerPing(url: String): ServerPing {
        return httpClient.get() {
            url(url)
        }
    }
}