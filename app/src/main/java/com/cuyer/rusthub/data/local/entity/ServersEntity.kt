package com.cuyer.rusthub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cuyer.rusthub.domain.model.Servers

@Entity
data class ServersEntity(
    val __v: Int,
    val _id: String,
    val cycle: String?,
    val difficulty: String?,
    val isOfficial: Boolean,
    val map_name: String,
    val max_group: String?,
    val modded: String,
    val name: String,
    val player_count: String,
    val rating: String,
    val server_flag: String,
    val wipe: String,
    val wipe_schedule: String?,
    @PrimaryKey val id: Int? = null
) {
    fun toServers(): Servers {
        return Servers(
            __v = __v,
            _id = _id,
            cycle = cycle,
            difficulty = difficulty,
            isOfficial = isOfficial,
            map_name = map_name,
            max_group = max_group,
            modded = modded,
            name = name,
            player_count = player_count,
            rating = rating,
            server_flag = server_flag,
            wipe = wipe,
            wipe_schedule = wipe_schedule
        )
    }
}
