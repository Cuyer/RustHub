package com.cuyer.rusthub.data.remote

import com.cuyer.rusthub.data.remote.dto.items.ItemsDto
import retrofit2.http.GET

interface ItemsApi {

    @GET("/items")
    suspend fun getItems(): List<ItemsDto>

}