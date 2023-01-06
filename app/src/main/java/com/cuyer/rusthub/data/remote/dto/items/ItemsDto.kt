package com.cuyer.rusthub.data.remote.dto.items

import com.cuyer.rusthub.data.local.entity.ItemsEntity
import com.cuyer.rusthub.domain.model.Items

data class ItemsDto(
    val Craftable: String,
    val Image: String,
    val Ingredients: List<Ingredient>,
    val Type: String,
    val _id: String
)

fun ItemsDto.toItemsEntity(): ItemsEntity {
    return ItemsEntity(
        Craftable = Craftable,
        Image = Image,
        Ingredients = Ingredients,
        Type = Type,
        _id = _id
    )
}