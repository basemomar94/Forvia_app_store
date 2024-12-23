package com.bassem.forvia_app_store.presentation.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Represents the UI model for an app, used for displaying app details in the UI and storing them in the database.
 *
 * This class is mapped to a Room database table named "apps_table"
 */
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
