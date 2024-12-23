package com.bassem.forvia_app_store.domain.usecase

import com.bassem.forvia_app_store.BaseTest
import com.bassem.forvia_app_store.data.local.AppsDao
import com.bassem.forvia_app_store.data.models.All
import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.data.models.AppsResult
import com.bassem.forvia_app_store.data.models.Data
import com.bassem.forvia_app_store.data.models.Datasets
import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.bassem.forvia_app_store.data.models.ListApps
import com.bassem.forvia_app_store.data.models.Responses
import com.bassem.forvia_app_store.domain.repository.AppsRepo
import com.bassem.forvia_app_store.domain.usecases.FetchAppsUseCase
import com.bassem.forvia_app_store.presentation.mapers.AppDtoToUiModelMapper
import com.bassem.forvia_app_store.presentation.models.AppsUi
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FetchAppsUseCaseTest : BaseTest() {

    private val appsRepo: AppsRepo = mockk(relaxed = true)
    private val mapper: AppDtoToUiModelMapper = mockk(relaxed = true)
    private val dao: AppsDao = mockk(relaxed = true)

    private val testScheduler = TestCoroutineScheduler()
    private val ioDispatcher = StandardTestDispatcher(testScheduler)

    private val fetchAppsUseCase = FetchAppsUseCase(
        appsRepo = appsRepo,
        mapper = mapper,
        dao = dao,
        ioDispatcher = ioDispatcher,
    )


    @Test
    fun `should fetch apps from repo, save to DAO, and return success`() = runTest(testScheduler) {

        val apiResponse = ApiResult.Success(mockApiData())
        val mappedApps = mockMappedApps()

        coEvery { appsRepo.getApps() } returns flowOf(apiResponse)
        every { mapper.mapList(any()) } returns mappedApps
        coEvery { dao.deleteAllApps() } just Runs
        coEvery { dao.upsertAllApps(mappedApps) } just Runs

        val result = fetchAppsUseCase().toList()

        assert(result.last() is ApiResult.Success)
        assertEquals(mappedApps, (result.last() as ApiResult.Success).data)

        coVerifySequence {
            appsRepo.getApps()
            mapper.mapList(apiResponse.data.responses.listApps.datasets.all.data.list)
            dao.deleteAllApps()
            dao.upsertAllApps(mappedApps)
        }
    }

    @Test
    fun `should return cached apps on failure`() = runTest(testScheduler) {
        coEvery { dao.getApps() } returns mockMappedApps()
        coEvery { appsRepo.getApps() } returns flowOf(ApiResult.Fail(ErrorTypes.IoException))

        val results = fetchAppsUseCase().toList()

        assert(results.last() is ApiResult.Success)
        assertEquals(mockMappedApps(), (results.last() as ApiResult.Success).data)

        coVerify { dao.getApps() }
        coVerify(exactly = 0) { dao.deleteAllApps() }
    }


    @Test
    fun `should return fail when no cached apps are available`() = runTest(ioDispatcher) {

        val apiError = ApiResult.Fail(ErrorTypes.IoException)

        coEvery { appsRepo.getApps() } returns flowOf(apiError)
        coEvery { dao.getApps() } returns emptyList()

        val result = fetchAppsUseCase().toList()

        assert(result.last() is ApiResult.Fail)
        assertEquals(ErrorTypes.IoException, (result.last() as ApiResult.Fail).errorTypes)

        coVerify {
            appsRepo.getApps()
            dao.getApps()
        }
    }

    private fun mockApiData(): AppsResult {
        return AppsResult(
            responses = Responses(ListApps(Datasets(All(Data()))))
        )
    }

    private fun mockMappedApps(): List<AppsUi> {
        return listOf(
            AppsUi(
                id = 0,
                name = "App1",
                iconUrl = "url1",
                rating = 4.5,
                downloads = 1000,
                modifiedAt = "2024-12-23",
                versionCode = 1,
                versionName = "1.0",
                size = 50,
                storeName = "Play Store"
            )
        )
    }
}
