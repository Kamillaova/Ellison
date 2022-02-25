package dev.fstudio.mc_discord_bot.api.mcapi

import dev.fstudio.mc_discord_bot.api.mcapi.ping.response.ServerPing
import io.ktor.client.*
import io.ktor.client.request.*

class MCApiImpl(private val httpClient: HttpClient) : MCApi {

    override suspend fun getServerPing(serverIp: String, serverPort: String): ServerPing {
        return httpClient.get {
            url("https://eu.mc-api.net/v3/server/ping/$serverIp:$serverPort")
        }
    }
}