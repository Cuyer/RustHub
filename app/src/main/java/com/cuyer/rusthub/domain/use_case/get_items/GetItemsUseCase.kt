package com.cuyer.rusthub.domain.use_case.get_items

import android.util.Log
import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.data.remote.dto.items.toItems
import com.cuyer.rusthub.domain.model.Items
import com.cuyer.rusthub.domain.repository.items.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemsRepository) {
    operator fun invoke(): Flow<Resource<List<Items>>> = flow {
        try {
            emit(Resource.Loading<List<Items>>())
            val items = repository.getItems().map { it.toItems() }
            emit(Resource.Success<List<Items>>(items))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Items>>(e.message() ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Items>>(e.message ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}