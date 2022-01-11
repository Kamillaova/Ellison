package dev.fstudio.mcworldstats.web.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.fstudio.mcworldstats.json
import dev.fstudio.mcworldstats.util.CommandArguments.host
import dev.fstudio.mcworldstats.util.CommandArguments.port
import dev.fstudio.mcworldstats.util.CommandArguments.ssl
import dev.fstudio.mcworldstats.web.api.McStatsApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideOkHttp() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

private fun provideOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(linkBuilder())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
}

private fun provideApiService(retrofit: Retrofit): McStatsApi {
    return retrofit.create(McStatsApi::class.java)
}

private fun linkBuilder(): String {
    val link = when {
        ssl -> "https"
        else -> "http"
    }
    return "$link://$host:$port"
}