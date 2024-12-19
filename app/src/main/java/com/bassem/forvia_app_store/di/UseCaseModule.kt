package com.bassem.forvia_app_store.di

import com.bassem.forvia_app_store.domain.repository.AppsRepo
import com.bassem.forvia_app_store.domain.usecases.FetchAppsUseCase
import com.bassem.forvia_app_store.presentation.mapers.AppDtoToUiModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideFetchAppsUseCase(appsRepo: AppsRepo, mapper: AppDtoToUiModelMapper) =
        FetchAppsUseCase(appsRepo, mapper)
}