import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "dev.fstudio"
version = "0.1"

repositories {
    mavenCentral()
}

application {
    mainClass.set("dev.fstudio.mcworldstats.MainKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Class"] = "dev.fstudio.mcworldstats.MainKt"
    }
}

dependencies {

    /*   CLI Args   */
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")

    /*   Ktor API   */
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("io.ktor:ktor-serialization:1.6.7")

    /*   Logger   */
    implementation("ch.qos.logback:logback-classic:1.2.10")

    /*   Exposed   */
    implementation("org.jetbrains.exposed:exposed-core:0.37.3")
    implementation("org.jetbrains.exposed:exposed-dao:0.37.3")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.3")

    /*   MySQL Connector   */
    implementation("mysql:mysql-connector-java:8.0.25")

    /*   Dependency Injection   */
    implementation("io.insert-koin:koin-core:3.1.4")

    /*   Network API Requests   */
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
}