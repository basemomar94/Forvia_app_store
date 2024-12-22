package com.bassem.forvia_app_store.domain.usecases

import com.bassem.forvia_app_store.data.local.AppsDao
import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.di.IoDispatcher
import com.bassem.forvia_app_store.domain.repository.AppsRepo
import com.bassem.forvia_app_store.presentation.mapers.AppDtoToUiModelMapper
import com.bassem.forvia_app_store.presentation.models.AppsUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.invoke
import javax.inject.Inject

class FetchAppsUseCase @Inject constructor(
    private val appsRepo: AppsRepo,
    private val mapper: AppDtoToUiModelMapper,
    private val dao: AppsDao,
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<ApiResult<List<AppsUi>>> {
        return appsRepo.getApps().map { apiResult ->
            when (apiResult) {
                is ApiResult.Success -> {
                    val appsList: List<AppsUi> =
                        mapper.mapList(apiResult.data.responses.listApps.datasets.all.data.list)
                    ioDispatcher {
                        dao.deleteAllApps()
                        dao.upsertAllApps(appsList)
                    }
                    ApiResult.Success(appsList)
                }

                is ApiResult.Fail -> {
                    val cachedApps: List<AppsUi> = ioDispatcher { dao.getApps() }
                    if (cachedApps.isNotEmpty()) {
                        ApiResult.Success(cachedApps)
                    } else {
                        ApiResult.Fail(apiResult.errorTypes)
                    }
                }

                is ApiResult.Loading -> ApiResult.Loading
            }
        }
    }
}

