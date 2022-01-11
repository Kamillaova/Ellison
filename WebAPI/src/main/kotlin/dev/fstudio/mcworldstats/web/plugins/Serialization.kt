package dev.fstudio.mcworldstats.web.plugins

import dev.fstudio.mcworldstats.json
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

fun Application.configureSerialization(){
    install(ContentNegotiation){
        json(json)
    }
}