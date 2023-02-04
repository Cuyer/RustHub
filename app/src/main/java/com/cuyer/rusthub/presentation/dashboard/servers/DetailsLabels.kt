package com.cuyer.rusthub.presentation.dashboard.servers

data class DetailsLabels(
    val label: String = "",
    var value: String = ""
)

fun DetailsLabels.toDetailsLabels(): List<DetailsLabels> {
    val labelsList = mutableListOf<DetailsLabels>()
    labelsList.add(DetailsLabels(label = "Wipe: "))
    labelsList.add(DetailsLabels(label = "Rating: "))
    labelsList.add(DetailsLabels(label = "Cycle: "))
    labelsList.add(DetailsLabels(label = "Player: "))
    labelsList.add(DetailsLabels(label = "Map: "))
    labelsList.add(DetailsLabels(label = "Max group: "))
    return labelsList
}