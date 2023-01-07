package com.cuyer.rusthub.domain.model

import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.data.remote.dto.items.ScrappedComponents

data class Items(
    val Craftable: String,
    val Image: String,
    val Ingredients: List<Ingredient>,
    val ScrappedComponents: List<ScrappedComponents>,
    val Type: String,
    val _id: String
)
