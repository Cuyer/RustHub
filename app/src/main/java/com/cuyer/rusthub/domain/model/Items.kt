package com.cuyer.rusthub.domain.model

import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.data.remote.dto.items.ScrappedComponents

data class Items(
    val craftable: String?,
    val image: String,
    val ingredients: List<Ingredient>,
    val scrappedComponents: List<ScrappedComponents>,
    val type: String,
    val _id: String
)
