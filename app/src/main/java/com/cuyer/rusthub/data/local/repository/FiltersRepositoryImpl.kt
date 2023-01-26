package com.cuyer.rusthub.data.local.repository

import androidx.lifecycle.LiveData
import com.cuyer.rusthub.data.local.FiltersDao
import com.cuyer.rusthub.data.local.entity.FiltersEntity
import com.cuyer.rusthub.domain.repository.filters.FiltersRepository

class FiltersRepositoryImpl(
    private val dao: FiltersDao
): FiltersRepository {
    override suspend fun insert(filter: FiltersEntity) {
        dao.insert(filter)
    }

    override suspend fun delete() {
        dao.delete()
    }

    override fun getAll(): LiveData<List<FiltersEntity>> {
        return dao.getAll()
    }
}