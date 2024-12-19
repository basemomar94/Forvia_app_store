package com.bassem.forvia_app_store.presentation.mapers

import com.bassem.forvia_app_store.data.models.AppDetails
import com.bassem.forvia_app_store.presentation.models.AppsUi
import javax.inject.Inject

class AppDtoToUiModelMapper @Inject constructor() {
    fun map(appDto: AppDetails): AppsUi {
        return AppsUi(
            id = appDto.id,
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
