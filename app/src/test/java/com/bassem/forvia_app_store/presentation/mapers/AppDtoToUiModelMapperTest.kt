package com.bassem.forvia_app_store.presentation.mapers

import com.bassem.forvia_app_store.BaseTest
import com.bassem.forvia_app_store.data.models.AppDetails
import com.bassem.forvia_app_store.presentation.models.AppsUi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AppDtoToUiModelMapperTest : BaseTest() {


    @Test
    fun `map should correctly map AppDetails to AppsUi`() {
        val appDetails = AppDetails(
            name = "Test App",
            icon = "https://example.com/icon.png",
            rating = 4.5,
            downloads = 1000,
            modified = "2024-12-23",
            vercode = 123,
            vername = "1.2.3",
            size = 50,
            store_name = "Play Store"
        )

        val expectedAppsUi = AppsUi(
            name = "Test App",
            iconUrl = "https://example.com/icon.png",
            rating = 4.5,
            downloads = 1000,
            modifiedAt = "2024-12-23",
            versionCode = 123,
            versionName = "1.2.3",
            size = 50,
            storeName = "Play Store"
        )

        val mapper = AppDtoToUiModelMapper()

        val actualAppsUi = mapper.mapList(listOf(appDetails)).first()

        assertEquals(expectedAppsUi, actualAppsUi)
    }

    @Test
    fun `mapList should correctly map a list of AppDetails to a list of AppsUi`() {
        val appDetailsList = listOf(
            AppDetails(
                name = "Test App 1",
                icon = "https://example.com/icon1.png",
                rating = 4.0,
                downloads = 2000,
                modified = "2024-12-23",
                vercode = 101,
                vername = "1.0.1",
                size = 40,
                store_name = "Play Store"
            ),
            AppDetails(
                name = "Test App 2",
                icon = "https://example.com/icon2.png",
                rating = 3.5,
                downloads = 500,
                modified = "2024-12-24",
                vercode = 102,
                vername = "1.0.2",
                size = 35,
                store_name = "App Store"
            )
        )

        val expectedAppsUiList = listOf(
            AppsUi(
                name = "Test App 1",
                iconUrl = "https://example.com/icon1.png",
                rating = 4.0,
                downloads = 2000,
                modifiedAt = "2024-12-23",
                versionCode = 101,
                versionName = "1.0.1",
                size = 40,
                storeName = "Play Store"
            ),
            AppsUi(
                name = "Test App 2",
                iconUrl = "https://example.com/icon2.png",
                rating = 3.5,
                downloads = 500,
                modifiedAt = "2024-12-24",
                versionCode = 102,
                versionName = "1.0.2",
                size = 35,
                storeName = "App Store"
            )
        )

        val mapper = AppDtoToUiModelMapper()

        val actualAppsUiList = mapper.mapList(appDetailsList)

        assertEquals(expectedAppsUiList, actualAppsUiList)
    }


}