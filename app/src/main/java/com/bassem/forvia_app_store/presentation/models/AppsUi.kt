package com.bassem.forvia_app_store.presentation.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "apps_table")
@Parcelize
data class AppsUi(
    val downloads: Int,
    val iconUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val modifiedAt: String,
    val name: String,
    val versionCode: Int,
    val versionName: String,
    val size: Long,
    val storeName: String,
    val rating: Double,
) : Parcelable
