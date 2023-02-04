package com.cuyer.rusthub.data.local.repository

import androidx.lifecycle.LiveData
import com.cuyer.rusthub.data.local.ServersFiltersDao
import com.cuyer.rusthub.data.local.entity.ServersFiltersEntity
import com.cuyer.rusthub.domain.repository.filters.ServersFiltersRepository

class ServersFiltersRepositoryImpl(
    private val dao: ServersFiltersDao
): ServersFiltersRepository {
    override suspend fun insert(filter: ServersFiltersEntity) {
        dao.insert(filter)
    }

    override suspend fun delete() {
        dao.delete()
    }

    override fun getAll(): LiveData<List<ServersFiltersEntity>> {
        return dao.getAll()
    }
}