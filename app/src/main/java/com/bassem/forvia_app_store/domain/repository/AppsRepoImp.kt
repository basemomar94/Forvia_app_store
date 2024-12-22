package com.bassem.forvia_app_store.domain.repository

import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.data.models.AppsResult
import com.bassem.forvia_app_store.data.remote.ApiService
import com.bassem.forvia_app_store.domain.utils.getExceptionMessage
import com.bassem.forvia_app_store.utils.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppsRepoImp @Inject constructor(
    private val apiService: ApiService
) : AppsRepo {
    private val log = Logger("AppsRepoImp")
    override suspend fun getApps(): Flow<ApiResult<AppsResult>> = flow {
        emit(ApiResult.Loading)
        try {
            val result = apiService.getAppsList()
            log.i("result is $result")
            emit(ApiResult.Success(result))
        } catch (e: Exception) {
            log.e("error is ${e.message}")
            emit(ApiResult.Fail(getExceptionMessage(e)))
        }
    }

}