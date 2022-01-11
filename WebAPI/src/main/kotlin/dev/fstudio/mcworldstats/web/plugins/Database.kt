package dev.fstudio.mcworldstats.web.plugins

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase(){
    Database.connect(
        url = "jdbc:mysql://goreloo7.beget.tech:3306/goreloo7_rivasub",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "goreloo7_rivasub",
        password = "gWRR7hF%Zx93b%pg"
    )
}