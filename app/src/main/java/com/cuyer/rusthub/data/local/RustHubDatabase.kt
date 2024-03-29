package com.cuyer.rusthub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cuyer.rusthub.data.local.entity.FiltersEntity
import com.cuyer.rusthub.data.local.entity.ItemsEntity
import com.cuyer.rusthub.data.local.entity.ServersEntity
import com.cuyer.rusthub.data.local.entity.ServersFiltersEntity

@Database(
    entities = [ItemsEntity::class, ServersEntity::class, FiltersEntity::class, ServersFiltersEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RustHubDatabase: RoomDatabase() {

    abstract val itemsDao: ItemsDao
    abstract val serversDao: ServersDao
    abstract val filtersDao: FiltersDao
    abstract val serversFiltersDao: ServersFiltersDao
}