package com.bassem.forvia_app_store.data.remote

import com.bassem.forvia_app_store.data.models.AppsResult
import retrofit2.http.GET

interface ApiService {

    @GET("listApps/")
   suspend fun getAppsList(): AppsResult
}