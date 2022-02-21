package dev.fstudio.mcworldstats.web.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

fun Application.configureSerialization(){
    install(ContentNegotiation){
        json(json)
    }
}