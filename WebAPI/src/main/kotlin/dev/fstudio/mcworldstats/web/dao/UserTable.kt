package dev.fstudio.mcworldstats.web.dao

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UserTable : Table("dle_users") {
    val name: Column<String> = varchar("name", 40)
    val uuid: Column<String> = char("uuid", 36, null)
    val abandoned: Column<Boolean> = bool("abandoned")
}