package com.cuyer.rusthub.domain.use_case.get_servers

import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.data.local.ServersDao
import com.cuyer.rusthub.data.remote.dto.servers.toServersEntity
import com.cuyer.rusthub.domain.model.Servers
import com.cuyer.rusthub.domain.repository.servers.ServersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetServersUseCase @Inject constructor(
    private val repository: ServersRepository,
    private val dao: ServersDao
) {
    operator fun invoke(): Flow<Resource<List<Servers>>> = flow {
        emit(Resource.Loading())
        val servers = dao.getAllServers().map { it.toServers() }
        emit(Resource.Loading(data = servers))
        if (servers.isEmpty()) {
            try {
                val remoteServers = repository.getServers()
                dao.deleteServers()
                dao.insertServers(remoteServers.map { it.toServersEntity() })
                val newServers = dao.getAllServers().map { it.toServers() }
                emit(Resource.Success(newServers))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message() ?: "An unexpected error occurred", data = servers))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        e.message ?: "Couldn't reach server. Check your internet connection.",
                        data = servers
                    )
                )
            }
        }
    }
}