package com.bassem.forvia_app_store.utils

import android.util.Log

class Logger(
    private val tag: String = "AppLogger",
    private val isEnabled: Boolean = true
) {

    fun d(message: String) {
        if (isEnabled) Log.d(tag, message)
    }

    fun i(message: String) {
        if (isEnabled) Log.i(tag, message)
    }

    fun w(message: String) {
        if (isEnabled) Log.w(tag, message)
    }

    fun e(message: String, throwable: Throwable? = null) {
        if (isEnabled) Log.e(tag, message, throwable)
    }

    fun v(message: String) {
        if (isEnabled) Log.v(tag, message)
    }
}
