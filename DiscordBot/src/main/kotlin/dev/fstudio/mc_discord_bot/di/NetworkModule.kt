package dev.fstudio.mc_discord_bot.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.fstudio.mc_discord_bot.api.mcapi.MCApi
import dev.fstudio.mc_discord_bot.api.mcworldstats.MCWorldApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val json = Json { ignoreUnknownKeys = true }

const val URL = "http://play.rivaviva.ru:1540/"
//const val URL = "http://localhost:8080/"

val networkModule = module {
    single { provideOkHttp() }
    single { provideRetrofit(get()) }
    single { provideMCApiService(get()) }
    single { provideMCStatsApiService(get()) }
}

private fun provideOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
}

private fun provideMCApiService(retrofit: Retrofit): MCApi {
    return retrofit.create(MCApi::class.java)
}

private fun provideMCStatsApiService(retrofit: Retrofit): MCWorldApi {
    return retrofit.create(MCWorldApi::class.java)
}