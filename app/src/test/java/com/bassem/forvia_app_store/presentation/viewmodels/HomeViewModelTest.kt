package com.bassem.forvia_app_store.presentation.viewmodels

import com.bassem.forvia_app_store.BaseTest
import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.bassem.forvia_app_store.domain.usecases.FetchAppsUseCase
import com.bassem.forvia_app_store.presentation.models.AppsUi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BaseTest() {

    private val fetchAppsUseCase = mockk<FetchAppsUseCase>(relaxed = true)

    @Test
    fun `fetchApps should emit Loading state followed by Data state on success`() = runTest {

        val appsUiList = listOf(
            AppsUi(
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

        coEvery { fetchAppsUseCase() } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Success(appsUiList))
        }

        val viewModel = HomeViewModel(fetchAppsUseCase)

        assertEquals(AppsScreenState.Loading, viewModel.appsScreenState.value)

        advanceUntilIdle()

        assertEquals(AppsScreenState.Data(appsUiList), viewModel.appsScreenState.value)
    }

    @Test
    fun `fetchApps should emit only Loading state when no data is emitted`() = runTest {

        coEvery { fetchAppsUseCase() } returns flow {
            emit(ApiResult.Loading)
        }

        val viewModel = HomeViewModel(fetchAppsUseCase)

        assertEquals(AppsScreenState.Loading, viewModel.appsScreenState.value)

        advanceUntilIdle()

        assertEquals(AppsScreenState.Loading, viewModel.appsScreenState.value)
    }

    @Test
    fun `fetchApps should emit Loading state followed by Error state on failure`() = runTest {

        val errorType = ErrorTypes.IoException

        coEvery { fetchAppsUseCase() } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Fail(errorType))
        }

        val viewModel = HomeViewModel(fetchAppsUseCase)

        assertEquals(AppsScreenState.Loading, viewModel.appsScreenState.value)

        advanceUntilIdle()

        assertEquals(AppsScreenState.Error(errorType), viewModel.appsScreenState.value)
    }

}