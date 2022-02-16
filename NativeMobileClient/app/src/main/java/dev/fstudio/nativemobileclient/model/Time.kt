package dev.fstudio.nativemobileclient.model

data class Time(
    val days: Int,
    val hours: Int,
    val minutes: Int
) {
    override fun toString(): String {
        return "Дней в игре: ${days}д (${hours}ч : ${minutes}м)"
    }
}