package com.cuyer.rusthub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ServersFiltersEntity(
    @PrimaryKey val id: Int? = null,
    val type: String = "",
    val value: String = ""
)
