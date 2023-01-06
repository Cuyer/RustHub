package com.cuyer.rusthub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.domain.model.Items

@Entity
data class ItemsEntity(
    val Craftable: String,
    val Image: String,
    val Ingredients: List<Ingredient>,
    val Type: String,
    val _id: String,
    @PrimaryKey val id: Int? = null
) {
    fun toItems(): Items {
        return Items(
            Craftable = Craftable,
            Image = Image,
            Ingredients = Ingredients,
            Type = Type,
            _id = _id
        )
    }
}
