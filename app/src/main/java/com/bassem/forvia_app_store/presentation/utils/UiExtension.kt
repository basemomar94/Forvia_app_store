package com.bassem.forvia_app_store.presentation.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bassem.forvia_app_store.R
import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun Context.getErrorMessage(errorType: ErrorTypes) = when (errorType) {
    is ErrorTypes.Generic -> errorType.message
        ?: getString(R.string.unexpected_error)
    ErrorTypes.IoException -> getString(R.string.net_work_error)
    ErrorTypes.JsonException -> getString(R.string.local_parsing_error)
    ErrorTypes.SqlException -> getString(R.string.remote_parsing_error)
}