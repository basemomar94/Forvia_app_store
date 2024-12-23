package com.bassem.forvia_app_store.domain.utils

import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.google.gson.JsonParseException
import java.io.IOException
import java.sql.SQLException

/**
 * Returns a corresponding error type based on the provided exception.
 *
 * This function maps specific exceptions to predefined error types for better error handling.
 * If the exception is not explicitly handled, it defaults to a generic error type containing the exception message.
 *
 * @param e The exception to be processed.
 * @return An [ErrorTypes] instance representing the mapped error type.
 */
fun getExceptionMessage(e: Throwable) = when (e) {
    is IOException -> ErrorTypes.IoException
    is SQLException -> ErrorTypes.SqlException
    is JsonParseException -> ErrorTypes.JsonException
    else -> ErrorTypes.Generic(e.message)
}