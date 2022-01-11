package dev.fstudio.mcworldstats.web.api

import dev.fstudio.mcworldstats.web.api.model.SimplifyStats
import retrofit2.http.GET
import retrofit2.http.Path

interface McStatsApi {
    @GET("/stats/{uuid}.json")
    suspend fun getSimplifyStats(
        @Path("uuid") uuid: String
    ) : SimplifyStats
}