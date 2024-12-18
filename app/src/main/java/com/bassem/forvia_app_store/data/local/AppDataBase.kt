package com.bassem.forvia_app_store.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bassem.forvia_app_store.data.models.AppDetails

//@Database(entities = [AppDetails::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun appsDao(): AppsDao

}