package com.cuyer.rusthub.data.remote.dto.servers

import com.cuyer.rusthub.data.local.entity.ServersEntity
import com.cuyer.rusthub.domain.model.Servers

data class ServersDto(
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
    val wipe_schedule: String?
)

fun ServersDto.toServersEntity(): ServersEntity{
    return ServersEntity(
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