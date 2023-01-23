package com.cuyer.rusthub.presentation.dashboard.calculators.crafting

import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.domain.model.CraftingItems

class CraftingDataHolder {
    companion object {
        private val dataList = mutableListOf<CraftingItems>()

        fun addData(data: CraftingItems) {
            dataList.add(data)
        }

        fun getData(): List<CraftingItems> {
            return dataList.toList() // return a copy of the list instead of the original
        }

        fun removeData(){
            dataList.clear()
        }
    }
}