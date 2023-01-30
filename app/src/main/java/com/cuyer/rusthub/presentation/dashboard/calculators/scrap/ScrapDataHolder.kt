package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import com.cuyer.rusthub.domain.model.CraftingItems

class ScrapDataHolder {
    companion object {
        private val dataList = mutableListOf<ScrapItems>()

        fun addData(data: ScrapItems) {
            dataList.add(data)
        }

        fun getData(): List<ScrapItems> {
            return dataList.toList() // return a copy of the list instead of the original
        }

        fun removeData(){
            dataList.clear()
        }
    }
}