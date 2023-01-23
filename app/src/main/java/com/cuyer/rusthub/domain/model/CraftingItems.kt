package com.cuyer.rusthub.domain.model

import com.cuyer.rusthub.data.remote.dto.items.Ingredient

data class CraftingItems(
     val mainIcon: String = "",
     val amount: String = "",
     val ingredients: List<Ingredient> = emptyList()
)
