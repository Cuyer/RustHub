package com.cuyer.rusthub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.data.remote.dto.items.ScrappedComponents
import com.cuyer.rusthub.domain.model.Items

@Entity
data class ItemsEntity(
    val craftable: String?,
    val image: String,
    val ingredients: List<Ingredient>,
    val scrappedComponents: List<ScrappedComponents>,
    val type: String,
    val _id: String,
    @PrimaryKey val id: Int? = null
) {
    fun toItems(): Items {
        return Items(
            craftable = craftable,
            image = image,
            ingredients = ingredients,
            scrappedComponents = scrappedComponents,
            type = type,
            _id = _id
        )
    }
}
