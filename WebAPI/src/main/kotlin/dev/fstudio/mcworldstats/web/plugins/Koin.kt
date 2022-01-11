package dev.fstudio.mcworldstats.web.plugins

import dev.fstudio.mcworldstats.web.di.networkModule
import io.ktor.application.*
import org.koin.core.context.startKoin

fun Application.configureKoin(){
    startKoin {
        modules(networkModule)
    }
}