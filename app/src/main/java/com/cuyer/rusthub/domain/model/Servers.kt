package com.cuyer.rusthub.domain.model

data class Servers( val __v: Int,
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
                    val serverIp: String,
                    val updateTime: String
)
