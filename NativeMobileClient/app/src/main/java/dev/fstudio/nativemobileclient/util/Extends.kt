package dev.fstudio.nativemobileclient.util

import dev.fstudio.nativemobileclient.model.Time
import kotlin.math.ln
import kotlin.math.pow

object Extends {
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

}