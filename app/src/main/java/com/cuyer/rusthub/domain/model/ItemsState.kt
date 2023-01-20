package com.cuyer.rusthub.domain.model

data class ItemsState(
    val isLoading: Boolean = false,
    val items: List<Items> = emptyList(),
    val error: String = ""
)
