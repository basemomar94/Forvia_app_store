package com.bassem.forvia_app_store.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppsUi(
    val downloads: Int,
    val iconUrl: String,
    val id: Int,
    val modifiedAt: String,
    val name: String,
    val versionCode: Int,
    val versionName: String,
    val size: Int,
    val storeName: String,
    val rating: Double,
) : Parcelable
