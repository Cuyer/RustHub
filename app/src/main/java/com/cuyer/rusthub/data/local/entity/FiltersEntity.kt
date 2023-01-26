package com.cuyer.rusthub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FiltersEntity(
    @PrimaryKey val id: Int? = null,
    val name: String
) {
}