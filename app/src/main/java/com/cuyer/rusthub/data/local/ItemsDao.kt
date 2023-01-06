package com.cuyer.rusthub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuyer.rusthub.data.local.entity.ItemsEntity

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemsEntity>)

    @Query("DELETE FROM itemsentity")
    suspend fun deleteItems()

    @Query("SELECT * FROM itemsentity")
    suspend fun getAllItems() : List<ItemsEntity>

    @Query("SELECT * FROM itemsentity WHERE Image LIKE '%' || :Image || '%'")
    suspend fun getItem(Image: String): List<ItemsEntity>

    @Query("SELECT * FROM itemsentity WHERE Image LIKE '%' || :Image || '%'" +
            " AND Craftable LIKE '%' || :Craftable || '%' " +
            "AND Type LIKE '%' || :Type || '%' ")
    suspend fun getFilteredItem(Image: String?, Craftable: String?, Type: String?) : List<ItemsEntity>
}
