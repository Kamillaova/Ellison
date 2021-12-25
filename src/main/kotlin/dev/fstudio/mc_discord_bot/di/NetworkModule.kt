@file:Suppress("EXPERIMENTAL_API_USAGE")

package dev.fstudio.mc_discord_bot.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.fstudio.mc_discord_bot.api.mcapi.MCApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val URL = "http://mcapi.us"

val networkModule = module {
    single { provideOkHttp() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

private fun provideOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
}

private fun provideApiService(retrofit: Retrofit): MCApi {
    return retrofit.create(MCApi::class.java)
}