package com.bassem.forvia_app_store.di

import com.bassem.forvia_app_store.data.local.AppsDao
import com.bassem.forvia_app_store.domain.repository.AppsRepo
import com.bassem.forvia_app_store.domain.usecases.FetchAppsUseCase
import com.bassem.forvia_app_store.presentation.mapers.AppDtoToUiModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchAppsUseCase(
        appsRepo: AppsRepo,
        mapper: AppDtoToUiModelMapper,
        dao: AppsDao,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) =
        FetchAppsUseCase(appsRepo, mapper, dao, ioDispatcher = dispatcher)
}