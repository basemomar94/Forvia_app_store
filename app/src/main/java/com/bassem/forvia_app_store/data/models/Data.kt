package com.bassem.forvia_app_store.data.models

data class Data(
    val hidden: Int,
    val limit: Int,
    val list: List<AppDetails>,
    val next: Int,
    val offset: Int,
    val total: Int
)