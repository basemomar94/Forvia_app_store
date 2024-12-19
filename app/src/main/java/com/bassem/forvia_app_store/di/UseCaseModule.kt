package com.bassem.forvia_app_store.di

import com.bassem.forvia_app_store.domain.repository.AppsRepo
import com.bassem.forvia_app_store.domain.usecases.FetchAppsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideFetchAppsUseCase(appsRepo: AppsRepo) = FetchAppsUseCase(appsRepo)
}