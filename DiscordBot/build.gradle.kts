import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.fstudio"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {

    /*   CLI Args   */
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")

    /*   Discord Bot API Library   */
    implementation("com.jessecorbett:diskord-bot:2.1.1")

    /*   Coroutines   */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    /*   Dependency Injection   */
    implementation("io.insert-koin:koin-core:3.1.4")

    /*   Network API Requests   */
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")


}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Class"] = "dev.fstudio.mc_discord_bot.MainKt"
    }
}

application {
    mainClass.set("dev.fstudio.mc_discord_bot.MainKt")
}