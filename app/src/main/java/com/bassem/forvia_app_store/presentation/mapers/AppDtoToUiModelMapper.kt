package com.bassem.forvia_app_store.presentation.mapers

import com.bassem.forvia_app_store.data.models.AppDetails
import com.bassem.forvia_app_store.presentation.models.AppsUi
import javax.inject.Inject

/**
 * Mapper class to convert data transfer objects (DTOs) of apps into UI models.
 *
 * This class is responsible for mapping `AppDetails` (DTO) to `AppsUi` (UI model) and provides functionality
 * to map a single DTO or a list of DTOs.
 *
 * @constructor Creates an instance of [AppDtoToUiModelMapper].
 */
class AppDtoToUiModelMapper @Inject constructor() {
    private fun map(appDto: AppDetails): AppsUi {
        return AppsUi(
            name = appDto.name,
            iconUrl = appDto.icon,
            rating = appDto.rating,
            downloads = appDto.downloads,
            modifiedAt = appDto.modified,
            versionCode = appDto.vercode,
            versionName = appDto.vername,
            size = appDto.size,
            storeName = appDto.store_name,
        )
    }

    fun mapList(dtos: List<AppDetails>): List<AppsUi> {
        return dtos.map { map(it) }
    }
}
