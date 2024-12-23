package com.bassem.forvia_app_store.data.models

data class AppDetails(
    val added: String = "",
    val apk_tags: List<String> = emptyList(),
    val downloads: Int = 0,
    val graphic: String = "",
    val icon: String = "",
    val id: Int = 0,
    val md5sum: String = "",
    val modified: String = "",
    val name: String = "",
    val `package`: String = "",
    val pdownloads: Int = 0,
    val rating: Double = 0.0,
    val size: Long = 0L,
    val store_id: Int = 0,
    val store_name: String = "",
    val updated: String = "",
    val uptype: String = "",
    val vercode: Int = 0,
    val vername: String = ""
)
