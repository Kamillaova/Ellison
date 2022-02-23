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

application {
    mainClass.set("dev.fstudio.mc_discord_bot.MainKt")
}

val createBuildClassFile = task("createBuildClassFile") {
    doFirst {
        val packagePath = "dev${File.separator}fstudio${File.separator}mc_discord_bot"
        val path =
            "src${File.separator}" +
                    "main${File.separator}" +
                    "kotlin${File.separator}" +
                    "$packagePath${File.separator}"

        File(path, "Build.kt").writeText(
            "package dev.fstudio.mc_discord_bot\n\n" +
                    "object Build {\n" +
                    "    const val VERSION = $version\n" +
                    "}"
        )
    }
}

tasks.withType<KotlinCompile> {
    dependsOn(createBuildClassFile)
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Class"] = "dev.fstudio.mc_discord_bot.MainKt"
    }
}

dependencies {
    /*   TOML Configuration file   */
    implementation("net.peanuuutz:tomlkt:0.1.0")

    /*   Discord Bot API Library   */
    implementation("com.jessecorbett:diskord-bot:2.1.1")

    /*   Coroutines   */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    /*   Dependency Injection   */
    implementation("io.insert-koin:koin-core:3.1.5")
    implementation("io.insert-koin:koin-ktor:3.1.5")

    /*   Network API Requests   */
    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("io.ktor:ktor-client-cio:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")

}