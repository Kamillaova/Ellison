package dev.fstudio.nativemobileclient.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.fstudio.nativemobileclient.api.MCWorldApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val json = Json { ignoreUnknownKeys = true }

private const val URL = "http://play.rivaviva.ru:1540"

@ExperimentalSerializationApi
val networkModule = module {
    single { provideOkHttp(get()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

private fun provideOkHttp(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .cache(Cache(context.cacheDir, 10485760))
        .followRedirects(true)
        .followSslRedirects(true)
        .build()
}

@ExperimentalSerializationApi
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
}

private fun provideApiService(retrofit: Retrofit): MCWorldApi {
    return retrofit.create(MCWorldApi::class.java)
}