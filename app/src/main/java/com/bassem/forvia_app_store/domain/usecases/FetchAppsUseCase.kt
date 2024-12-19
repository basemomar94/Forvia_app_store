package com.bassem.forvia_app_store.domain.usecases

import com.bassem.forvia_app_store.domain.repository.AppsRepo
import javax.inject.Inject

class FetchAppsUseCase @Inject constructor(private val appsRepo: AppsRepo) {

    suspend operator fun invoke() = appsRepo.getProperties()
}