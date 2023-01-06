package com.cuyer.rusthub.domain.repository.servers

import com.cuyer.rusthub.data.remote.dto.servers.ServersDto

interface ServersRepository {

    suspend fun getServers(): List<ServersDto>
}