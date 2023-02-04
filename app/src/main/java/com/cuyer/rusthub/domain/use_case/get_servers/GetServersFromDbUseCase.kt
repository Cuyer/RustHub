package com.cuyer.rusthub.domain.use_case.get_servers

import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.data.local.ItemsDao
import com.cuyer.rusthub.data.local.ServersDao
import com.cuyer.rusthub.domain.model.Items
import com.cuyer.rusthub.domain.model.Servers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetServersFromDbUseCase @Inject constructor(
    private val dao: ServersDao
) {
    operator fun invoke(): Flow<Resource<List<Servers>>> = flow {
        emit(Resource.Loading())
        val servers = dao.getAllServers().map { it.toServers() }
        emit(Resource.Loading(data = servers))
        if (servers.isNotEmpty()) {
            emit(Resource.Success(data = servers))
        }
    }
}