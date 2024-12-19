package com.bassem.forvia_app_store.domain.repository

import com.bassem.forvia_app_store.data.models.ApiResult
import kotlinx.coroutines.flow.Flow

interface AppsRepo {

   suspend fun getApps(): Flow<ApiResult<Any?>>
}