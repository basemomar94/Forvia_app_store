package com.bassem.forvia_app_store.data.remote

import retrofit2.http.GET

interface ApiService {

    @GET("listApps/")
   suspend fun getAppsList(): ApiResult
}