package com.bassem.forvia_app_store.domain.utils

import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.google.gson.JsonParseException
import java.io.IOException
import java.sql.SQLException

fun getExceptionMessage(e: Throwable) = when (e) {
    is IOException -> ErrorTypes.IoException
    is SQLException -> ErrorTypes.SqlException
    is JsonParseException -> ErrorTypes.JsonException
    else -> ErrorTypes.Generic(e.message)
}