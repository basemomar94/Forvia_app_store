package com.bassem.forvia_app_store.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Target(
   AnnotationTarget.PROPERTY,
   AnnotationTarget.VALUE_PARAMETER,
   AnnotationTarget.FUNCTION,
   AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@Qualifier
annotation class IoDispatcher


@Module
@InstallIn(SingletonComponent::class)
 object DispatchersModule {

    @Provides
    @IoDispatcher
    fun provideIoDispatcher() : CoroutineDispatcher = Dispatchers.IO
}