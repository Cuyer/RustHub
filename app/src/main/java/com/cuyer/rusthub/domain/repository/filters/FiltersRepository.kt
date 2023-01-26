package com.cuyer.rusthub.domain.repository.filters

import androidx.lifecycle.LiveData
import com.cuyer.rusthub.data.local.entity.FiltersEntity

interface FiltersRepository {

    suspend fun insert(filter: FiltersEntity)

    suspend fun delete()

    fun getAll(): LiveData<List<FiltersEntity>>
}