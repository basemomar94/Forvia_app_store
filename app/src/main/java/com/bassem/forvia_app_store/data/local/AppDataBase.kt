package com.bassem.forvia_app_store.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bassem.forvia_app_store.data.models.AppDetails
import com.bassem.forvia_app_store.presentation.models.AppsUi

@Database(entities = [AppsUi::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun appsDao(): AppsDao

}