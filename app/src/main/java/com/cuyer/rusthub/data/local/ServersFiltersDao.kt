package com.cuyer.rusthub.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuyer.rusthub.data.local.entity.FiltersEntity
import com.cuyer.rusthub.data.local.entity.ServersFiltersEntity

@Dao
interface ServersFiltersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filter: ServersFiltersEntity)

    @Query("DELETE FROM serversfiltersentity")
    suspend fun delete()

    @Query("SELECT * FROM serversfiltersentity")
    fun getAll(): LiveData<List<ServersFiltersEntity>>

}