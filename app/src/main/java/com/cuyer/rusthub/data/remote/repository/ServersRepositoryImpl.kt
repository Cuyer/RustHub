package com.cuyer.rusthub.data.remote.repository

import com.cuyer.rusthub.data.remote.ServersApi
import com.cuyer.rusthub.data.remote.dto.servers.ServersDto
import com.cuyer.rusthub.domain.repository.servers.ServersRepository
import javax.inject.Inject

class ServersRepositoryImpl @Inject constructor(
    private val api: ServersApi): ServersRepository {

    override suspend fun getServers(): List<ServersDto> {
        return api.getServers()
    }
}