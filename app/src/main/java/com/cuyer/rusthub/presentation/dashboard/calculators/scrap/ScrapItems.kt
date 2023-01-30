package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.data.remote.dto.items.Scrap

data class ScrapItems(
var mainIcon: String = "",
var amount: String = "",
var scrap: List<Scrap> = emptyList()
)
