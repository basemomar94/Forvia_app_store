package com.bassem.forvia_app_store.di

import com.bassem.forvia_app_store.presentation.mapers.AppDtoToUiModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MappersModule {

    @Provides
    fun provideDetailsMappers () = AppDtoToUiModelMapper()
}