package com.cuyer.rusthub.domain.repository.items

import com.cuyer.rusthub.data.remote.dto.items.ItemsDto

interface ItemsRepository {

    suspend fun getItems(): List<ItemsDto>
}