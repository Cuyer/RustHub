package com.cuyer.rusthub.domain.use_case.get_items

import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.data.local.ItemsDao
import com.cuyer.rusthub.domain.model.Items
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemsFromDbUseCase @Inject constructor(
    private val dao: ItemsDao
) {
    operator fun invoke(): Flow<Resource<List<Items>>> = flow {
        emit(Resource.Loading())
        val items = dao.getAllItems().map { it.toItems() }
        emit(Resource.Loading(data = items))
        if (items.isNotEmpty()) {
            emit(Resource.Success(data = items))
        }
    }
}