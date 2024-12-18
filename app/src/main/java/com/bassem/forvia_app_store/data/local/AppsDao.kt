package com.bassem.forvia_app_store.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bassem.forvia_app_store.data.models.AppDetails
import kotlinx.coroutines.flow.Flow
@Dao
interface AppsDao {
  /*  @Upsert
    suspend fun upsertAllApps(apps: List<AppDetails>)

    @Query("SELECT * FROM apps_table")
    fun getApps(): Flow<List<AppDetails>>

    @Query("DELETE  FROM apps_table")
   suspend fun deleteAllApps()*/
}
