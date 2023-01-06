package com.cuyer.rusthub.domain.use_case.get_items

import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.data.local.ItemsDao
import com.cuyer.rusthub.data.remote.dto.items.toItemsEntity
import com.cuyer.rusthub.domain.model.Items
import com.cuyer.rusthub.domain.repository.items.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemsRepository,
    private val dao: ItemsDao) {
    operator fun invoke(): Flow<Resource<List<Items>>> = flow {
        emit(Resource.Loading())
        val items = dao.getAllItems().map { it.toItems() }
        emit(Resource.Loading(data = items))

        try {
            val remoteItems = repository.getItems()
            dao.deleteItems()
            dao.insertItems(remoteItems.map { it.toItemsEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(e.message() ?: "An unexpected error occurred", data = items ))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Couldn't reach server. Check your internet connection.", data = items))
        }

        val newItems = dao.getAllItems().map { it.toItems() }
        emit(Resource.Success(newItems))
    }
}