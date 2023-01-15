package com.cuyer.rusthub.presentation.dashboard.calculators

import androidx.fragment.app.Fragment
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.dashboard.DashboardListModel

data class CalculatorsListModel(
    val image: Int = 0,
    val text: String = "",
    val action: Fragment? = null,
    val tag: String = ""
)

fun CalculatorsListModel.toListModel(): List<CalculatorsListModel>{
    val list = mutableListOf<CalculatorsListModel>()
    list.add(CalculatorsListModel(R.drawable.ic_crafting_calculator, "Crafting"))
    list.add(CalculatorsListModel(R.drawable.ic_raid_calculator, "Raid"))
    list.add(CalculatorsListModel(R.drawable.ic_scrap_calculator, "Scrap"))
    return list
}
