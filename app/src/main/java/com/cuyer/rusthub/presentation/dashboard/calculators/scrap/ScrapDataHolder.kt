package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import com.cuyer.rusthub.data.remote.dto.items.Ingredient
import com.cuyer.rusthub.data.remote.dto.items.Scrap
import com.cuyer.rusthub.domain.model.CraftingItems

class ScrapDataHolder {
    companion object {
        private val dataList = mutableListOf<ScrapItems>()

        fun addData(data: ScrapItems) {
            dataList.add(data)
        }

        fun getData(): List<ScrapItems> {
            return dataList.groupBy { it.mainIcon }
                .map { (mainIcon, group) ->
                    val scraps = group.flatMap { it.scrap }
                        .groupBy { it.src }
                        .map { (src, scrapGroup) ->
                            Scrap(scrapGroup.first().name, src, scrapGroup.sumByDouble { it.amount.toDouble() }.toString())
                        }
                    ScrapItems(mainIcon, group.sumByDouble { it.amount.toDouble() }.toString(), scraps)
                }.toMutableList()
        }

        fun removeData(){
            dataList.clear()
        }
    }
}