package com.bassem.forvia_app_store.data.models


data class AppDetails(
    val added: String,
    val apk_tags: List<String>,
    val downloads: Int,
    val graphic: String,
    val icon: String,
    val id: Int,
    val md5sum: String,
    val modified: String,
    val name: String,
    val `package`: String,
    val pdownloads: Int,
    val rating: Double,
    val size: Long,
    val store_id: Int,
    val store_name: String,
    val updated: String,
    val uptype: String,
    val vercode: Int,
    val vername: String
)