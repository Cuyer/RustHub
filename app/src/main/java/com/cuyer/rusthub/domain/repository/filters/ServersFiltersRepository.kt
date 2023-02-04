package com.cuyer.rusthub.domain.repository.filters

import androidx.lifecycle.LiveData
import com.cuyer.rusthub.data.local.entity.ServersFiltersEntity

interface ServersFiltersRepository {

    suspend fun insert(filter: ServersFiltersEntity)

    suspend fun delete()

    fun getAll(): LiveData<List<ServersFiltersEntity>>

}