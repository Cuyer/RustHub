package com.cuyer.rusthub.domain.model

import com.cuyer.rusthub.data.remote.dto.items.Ingredient

data class Items(
    val Craftable: String,
    val Image: String,
    val Ingredients: List<Ingredient>,
    val Type: String,
    val _id: String
)
