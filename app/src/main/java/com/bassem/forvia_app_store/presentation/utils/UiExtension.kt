package com.bassem.forvia_app_store.presentation.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bassem.forvia_app_store.R
import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun Context.getErrorMessage(errorType: ErrorTypes?) = when (errorType) {
    is ErrorTypes.Generic -> errorType.message
        ?: getString(R.string.unexpected_error)

    ErrorTypes.IoException -> getString(R.string.net_work_error)
    ErrorTypes.JsonException -> getString(R.string.local_parsing_error)
    ErrorTypes.SqlException -> getString(R.string.remote_parsing_error)
    null -> getString(R.string.unexpected_error)
}

fun formatToLocalDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    val outputFormat = SimpleDateFormat.getDateTimeInstance(
        SimpleDateFormat.LONG,
        SimpleDateFormat.SHORT,
        Locale.getDefault()
    )

    return try {
        val date = inputFormat.parse(dateString)
        if (date != null) {
            outputFormat.format(date)
        } else throw Exception("Invalid Date")
    } catch (e: Exception) {
        "Invalid Date"
    }
}