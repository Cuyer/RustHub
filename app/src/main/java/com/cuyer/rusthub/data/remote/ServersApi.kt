package com.cuyer.rusthub.data.remote

import com.cuyer.rusthub.data.remote.dto.servers.ServersDto
import retrofit2.http.GET

interface ServersApi {

    @GET("/servers")
    suspend fun getServers(): List<ServersDto>
}