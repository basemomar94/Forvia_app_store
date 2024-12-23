package com.bassem.forvia_app_store.domain.repo

import android.util.Log
import com.bassem.forvia_app_store.BaseTest
import com.bassem.forvia_app_store.data.models.All
import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.data.models.AppsResult
import com.bassem.forvia_app_store.data.models.Data
import com.bassem.forvia_app_store.data.models.Datasets
import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.bassem.forvia_app_store.data.models.ListApps
import com.bassem.forvia_app_store.data.models.Responses
import com.bassem.forvia_app_store.data.remote.ApiService
import com.bassem.forvia_app_store.domain.repository.AppsRepoImp
import com.bassem.forvia_app_store.domain.utils.getExceptionMessage
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.IOException

class AppsRepoImpTest : BaseTest() {
    private val apiService: ApiService = mockk(relaxed = true)
    private val repository = AppsRepoImp(apiService)

    override fun setup() {
        super.setup()
        mockkStatic(Log::class)
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(),any(),any()) } returns 0
    }

    @Test
    fun `getApps should emit Loading and Success when API call is successful`() = runTest {

        val appsResult = AppsResult(
            responses = Responses(ListApps(Datasets(All(Data()))))
        )

        coEvery { apiService.getAppsList() } returns appsResult

        val results = repository.getApps().toList()

        assertEquals(ApiResult.Loading, results[0])
        assertEquals(ApiResult.Success(appsResult), results[1])

    }

    @Test
    fun `getApps should emit Loading and Error when API call is fail`() = runTest {

        val exception = IOException("Network error")

        coEvery { apiService.getAppsList() } throws exception

        mockkStatic(::getExceptionMessage)

        every { getExceptionMessage(exception) } returns ErrorTypes.IoException

        val results = repository.getApps().toList()

        assertEquals(ApiResult.Loading, results[0])
        assertEquals(ApiResult.Fail(ErrorTypes.IoException), results[1])
    }

}