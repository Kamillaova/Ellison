package dev.fstudio.mcworldstats.web.plugins

import dev.fstudio.mcworldstats.config
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase(){
    Database.connect(
        url = config.database.url,
        driver = config.database.driver,
        user = config.database.user,
        password = config.database.password
    )
}