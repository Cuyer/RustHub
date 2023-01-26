package com.cuyer.rusthub.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cuyer.rusthub.data.local.entity.FiltersEntity

@Dao
interface FiltersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filter: FiltersEntity)

    @Query("DELETE FROM filtersentity")
    suspend fun delete()

    @Query("SELECT * FROM filtersentity")
    fun getAll(): LiveData<List<FiltersEntity>>
}