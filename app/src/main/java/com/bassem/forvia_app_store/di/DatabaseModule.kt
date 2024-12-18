package com.bassem.forvia_app_store.di

import android.content.Context
import androidx.room.Room
import com.bassem.forvia_app_store.data.local.AppDataBase
import com.bassem.forvia_app_store.data.local.AppsDao
import com.bassem.forvia_app_store.data.utils.DatabaseConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).build()

    @Provides
    fun provideAppsDao(appDatabase: AppDataBase): AppsDao {
        return appDatabase.appsDao()
    }

}
