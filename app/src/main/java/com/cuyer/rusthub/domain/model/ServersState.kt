package com.cuyer.rusthub.domain.model

data class ServersState(
    val isLoading: Boolean = true,
    val servers: List<Servers> = emptyList(),
    val error: String = ""
)
