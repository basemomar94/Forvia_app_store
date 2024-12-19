package com.bassem.forvia_app_store.domain.usecases

import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.data.models.AppsResult
import com.bassem.forvia_app_store.domain.repository.AppsRepo
import com.bassem.forvia_app_store.presentation.mapers.AppDtoToUiModelMapper
import com.bassem.forvia_app_store.presentation.models.AppsUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchAppsUseCase @Inject constructor(
    private val appsRepo: AppsRepo,
    private val mapper: AppDtoToUiModelMapper
) {

    suspend operator fun invoke(): Flow<ApiResult<List<AppsUi>>> {
        return appsRepo.getApps().map { apiResult ->
            when (apiResult) {
                is ApiResult.Success -> ApiResult.Success(mapper.mapList((apiResult.data as AppsResult).responses.listApps.datasets.all.data.list))
                is ApiResult.Fail -> ApiResult.Fail(apiResult.errorTypes)
                is ApiResult.Loading -> ApiResult.Loading
            }
        }
    }
}

