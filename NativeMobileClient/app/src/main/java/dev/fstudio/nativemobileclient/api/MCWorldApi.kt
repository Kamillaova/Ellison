package dev.fstudio.nativemobileclient.api

import retrofit2.http.GET
import retrofit2.http.Path

interface MCWorldApi {
    @GET("player/stats/{username}")
    fun getStats(
        @Path("username") username: String
    ): Stats

    @GET("top")
    suspend fun getPlayersTop(): List<Player>

    @GET("allplayers")
    fun getAllPlayers(): List<Player>
}