package dev.fstudio.mcworldstats.web.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object UserOnlineStatsTable : IntIdTable("user_online_stats") {
    val players = reference("players", Players)
    val timestamp = datetime("timestamp").defaultExpression(CurrentDateTime())
}

class UserOnlineStat(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<UserOnlineStat>(UserOnlineStatsTable)
    var player by Player referencedOn UserOnlineStatsTable.players
    var timestamp by UserOnlineStatsTable.timestamp
}

object Players : IntIdTable("players_timestamp") {
    val name = varchar("name", 40)
}

class Player(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Player>(Players)
    var name by Players.name
}


// Stat(id, timestamp, List[Player])