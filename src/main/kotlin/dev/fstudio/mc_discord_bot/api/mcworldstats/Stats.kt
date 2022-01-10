package dev.fstudio.mc_discord_bot.api.mcworldstats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("minecraft:boat_one_cm")
    val minecraftBoatOneCm: Int = 0,
    @SerialName("minecraft:climb_one_cm")
    val minecraftClimbOneCm: Int = 0,
    @SerialName("minecraft:deaths")
    val minecraftDeaths: Int = 0,
    @SerialName("minecraft:drop")
    val minecraftDrop: Int = 0,
    @SerialName("minecraft:enchant_item")
    val minecraftEnchantItem: Int = 0,
    @SerialName("minecraft:fly_one_cm")
    val minecraftFlyOneCm: Int = 0,
    @SerialName("minecraft:jump")
    val minecraftJump: Int = 0,
    @SerialName("minecraft:mob_kills")
    val minecraftMobKills: Int = 0,
    @SerialName("minecraft:play_one_minute")
    val minecraftPlayOneMinute: Int = 0,
    @SerialName("minecraft:player_kills")
    val minecraftPlayerKills: Int = 0,
    @SerialName("minecraft:crouch_one_cm")
    val minecraftCrouchOneCm: Int = 0,
    @SerialName("minecraft:sprint_one_cm")
    val minecraftSprintOneCm: Int = 0,
    @SerialName("minecraft:swim_one_cm")
    val minecraftSwimOneCm: Int = 0,
    @SerialName("minecraft:time_since_death")
    val minecraftTimeSinceDeath: Int = 0,
    @SerialName("minecraft:walk_on_water_one_cm")
    val minecraftWalkOnWaterOneCm: Int = 0,
    @SerialName("minecraft:walk_one_cm")
    val minecraftWalkOneCm: Int = 0,
    @SerialName("minecraft:walk_under_water_one_cm")
    val minecraftWalkUnderWaterOneCm: Int = 0
)