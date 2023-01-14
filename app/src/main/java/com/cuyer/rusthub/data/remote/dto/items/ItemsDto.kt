package com.cuyer.rusthub.data.remote.dto.items

import com.cuyer.rusthub.data.local.entity.ItemsEntity
import com.cuyer.rusthub.domain.model.Items

data class ItemsDto(
    val craftable: String,
    val image: String,
    val ingredients: List<Ingredient>,
    val scrappedComponents: List<ScrappedComponents>,
    val type: String,
    val _id: String
)

fun ItemsDto.toItemsEntity(): ItemsEntity {
    return ItemsEntity(
        craftable = craftable,
        image = image,
        ingredients = ingredients,
        scrappedComponents = scrappedComponents,
        type = type,
        _id = _id
    )
}