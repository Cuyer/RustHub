package com.cuyer.rusthub.domain.use_case.get_servers

import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.data.remote.dto.items.toItems
import com.cuyer.rusthub.data.remote.dto.servers.toServers
import com.cuyer.rusthub.domain.model.Items
import com.cuyer.rusthub.domain.model.Servers
import com.cuyer.rusthub.domain.repository.items.ItemsRepository
import com.cuyer.rusthub.domain.repository.servers.ServersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetServersUseCase @Inject constructor(
    private val repository: ServersRepository
) {
    operator fun invoke(): Flow<Resource<List<Servers>>> = flow {
        try {
            emit(Resource.Loading<List<Servers>>())
            val servers = repository.getServers().map { it.toServers() }
            emit(Resource.Success<List<Servers>>(servers))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Servers>>(e.message() ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Servers>>(e.message ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}