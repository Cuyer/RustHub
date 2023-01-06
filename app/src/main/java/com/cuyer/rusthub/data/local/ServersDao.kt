package com.cuyer.rusthub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuyer.rusthub.data.local.entity.ItemsEntity
import com.cuyer.rusthub.data.local.entity.ServersEntity

@Dao
interface ServersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServers(servers: List<ServersEntity>)

    @Query("DELETE FROM serversentity")
    suspend fun deleteServers()

    @Query("SELECT * FROM serversentity")
    suspend fun getAllServers(): List<ServersEntity>

    @Query("SELECT * FROM serversentity WHERE name LIKE '%' || :name || '%'")
    suspend fun getServer(name: String): List<ServersEntity>

    @Query("SELECT * FROM serversentity WHERE name LIKE '%' || :name || '%'" +
            "AND wipe LIKE '%' || :wipe || '%' " +
            "AND rating LIKE '%' || :rating || '%'" +
            "AND modded LIKE '%' || :modded || '%'" +
            "AND player_count LIKE '%' || :player_count || '%'" +
            "AND map_name LIKE '%' || :map_name || '%'" +
            "AND cycle LIKE '%' || :cycle || '%'" +
            "AND server_flag LIKE '%' || :server_flag || '%'" +
            "AND max_group LIKE '%' || :max_group || '%'" +
            "AND difficulty LIKE '%' || :difficulty || '%'" +
            "AND wipe_schedule LIKE '%' || :wipe_schedule || '%'" +
            "AND isOfficial LIKE '%' || :isOfficial || '%'")
    suspend fun getFilteredServers(name: String?,
    wipe: String?,
    rating: String?,
    modded: String?,
    player_count: String?,
    map_name: String?,
    cycle: String?,
    server_flag: String?,
    max_group: String?,
    difficulty: String?,
    wipe_schedule: String?,
    isOfficial: String?) : List<ServersEntity>
}