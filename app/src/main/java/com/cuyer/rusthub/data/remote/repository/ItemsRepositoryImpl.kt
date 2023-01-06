package com.cuyer.rusthub.data.remote.repository

import com.cuyer.rusthub.data.remote.ItemsApi
import com.cuyer.rusthub.data.remote.dto.items.ItemsDto
import com.cuyer.rusthub.domain.repository.items.ItemsRepository
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val api: ItemsApi): ItemsRepository {

    override suspend fun getItems(): List<ItemsDto> {
        return api.getItems()
    }
}