package com.cuyer.rusthub.presentation.dashboard

import androidx.fragment.app.Fragment
import com.cuyer.rusthub.R

data class DashboardListModel(
    val image: Int = 0,
    val text: String = "",
    val action: Fragment? = null
)

fun DashboardListModel.toListModel(): List<DashboardListModel>{
    val list = mutableListOf<DashboardListModel>()
    list.add(DashboardListModel(R.drawable.ic_calculators_tab, "Calculators"))
    list.add(DashboardListModel(R.drawable.ic_genetics_tab, "Genetics"))
    list.add(DashboardListModel(R.drawable.ic_servers_tab, "Servers"))
    list.add(DashboardListModel(R.drawable.ic_settings_tab, "Settings"))
    return list
}
