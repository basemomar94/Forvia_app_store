package com.bassem.forvia_app_store.di

import com.bassem.forvia_app_store.domain.repository.AppsRepo
import com.bassem.forvia_app_store.domain.repository.AppsRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {

    @Binds
    abstract fun providesAppsRepo(impl: AppsRepoImp): AppsRepo
}