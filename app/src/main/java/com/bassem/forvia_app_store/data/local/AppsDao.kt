package com.bassem.forvia_app_store.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bassem.forvia_app_store.presentation.models.AppsUi

@Dao
interface AppsDao {
   @Upsert
    suspend fun upsertAllApps(apps: List<AppsUi>)

    @Query("SELECT * FROM apps_table")
    fun getApps(): List<AppsUi>

    @Query("DELETE  FROM apps_table")
   suspend fun deleteAllApps()
}
