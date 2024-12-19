package com.bassem.forvia_app_store.domain.repository

import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.data.remote.ApiService
import com.bassem.forvia_app_store.domain.utils.getExceptionMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppsRepoImp @Inject constructor(
    private val apiService: ApiService
) : AppsRepo {
    override suspend fun getApps() = flow {
        emit(ApiResult.Loading)
        try {
            val result = apiService.getAppsList()
            emit(ApiResult.Success(result))
        } catch (e: Exception) {
            emit(ApiResult.Fail(getExceptionMessage(e)))
        }
    }

}