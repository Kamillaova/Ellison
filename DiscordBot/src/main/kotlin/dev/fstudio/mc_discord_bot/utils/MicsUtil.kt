package dev.fstudio.mc_discord_bot.utils

import com.jessecorbett.diskord.api.common.Color
import com.jessecorbett.diskord.util.Colors
import dev.fstudio.mc_discord_bot.api.mcworldstats.Stats
import dev.fstudio.mc_discord_bot.model.Time
import kotlin.math.ln
import kotlin.math.pow
import kotlin.random.Random

object MicsUtil {

    fun getRandomColor(): Color {
        val random = Random(System.currentTimeMillis())
        return Colors.rgb(
            random.nextInt(0, 255), random.nextInt(0, 255), random.nextInt(0, 255)
        )
    }

    fun getGroundWalkedDistance(data: Stats): Int = data.minecraftWalkOneCm +
            data.minecraftSprintOneCm +
            data.minecraftClimbOneCm +
            data.minecraftCrouchOneCm

    fun getSwamDistance(data: Stats): Int = data.minecraftWalkOnWaterOneCm +
            data.minecraftBoatOneCm +
            data.minecraftWalkUnderWaterOneCm +
            data.minecraftSwimOneCm

    fun getAllBlocks(data: Stats): Int = getGroundWalkedDistance(data) +
            getSwamDistance(data) +
            data.minecraftFlyOneCm +
            data.minecraftFlyOneCm

    fun Int.tickToTime(): Time {
        var timeInteger = this / 20
        val day: Int = timeInteger / (24 * 3600)

        timeInteger %= (24 * 3600)
        val hour: Int = timeInteger / 3600

        timeInteger %= 3600
        val minutes: Int = timeInteger / 60

        return Time(day, hour, minutes)
    }

    fun Int.withSuffix(): String {
        if (this < 1000) return "" + this
        val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
        return String.format(
            "%.1f %c",
            this / 1000.0.pow(exp.toDouble()),
            "kMGTPE"[exp - 1]
        )
    }

    fun String.fixUnderline(): String = this.replace("_", "\\_")

    fun String.convertToDead(isDead: Boolean?): String = if (isDead == true) "~~$this~~" else this

    fun Long.toRoleMention(): String {
        return String.format("<@&%d>", this)
    }


}