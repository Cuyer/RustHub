package com.cuyer.rusthub.presentation.dashboard.calculators

import androidx.fragment.app.Fragment
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.dashboard.DashboardListModel
import com.cuyer.rusthub.presentation.dashboard.calculators.crafting.CraftingFragment
import com.cuyer.rusthub.presentation.dashboard.calculators.raid.RaidFragment
import com.cuyer.rusthub.presentation.dashboard.calculators.scrap.ScrapFragment

data class CalculatorsListModel(
    val image: Int = 0,
    val text: String = "",
    val action: Fragment? = null,
    val tag: String = ""
)

fun CalculatorsListModel.toListModel(): List<CalculatorsListModel>{
    val list = mutableListOf<CalculatorsListModel>()
    list.add(CalculatorsListModel(R.drawable.ic_crafting_calculator, "Crafting", CraftingFragment(), "crafting_fragment"))
    list.add(CalculatorsListModel(R.drawable.ic_raid_calculator, "Raid", RaidFragment(), "raid_fragment"))
    list.add(CalculatorsListModel(R.drawable.ic_scrap_calculator, "Scrap", ScrapFragment(), "scrap_fragment"))
    return list
}
