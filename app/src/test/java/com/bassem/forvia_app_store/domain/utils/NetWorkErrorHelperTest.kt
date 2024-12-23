package com.bassem.forvia_app_store.domain.utils

import com.bassem.forvia_app_store.BaseTest
import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.google.gson.JsonParseException
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.IOException
import java.sql.SQLException

class NetWorkErrorHelperTest : BaseTest() {

    @ParameterizedTest
    @MethodSource("provideExceptions")
    fun `test handling errors with expected message`(exception: Exception, expectedError: ErrorTypes) = runTest {

        val actualMessage = getExceptionMessage(exception)

        Assertions.assertEquals(expectedError, actualMessage)
    }

    companion object {
        @JvmStatic
        fun provideExceptions() = listOf(
            Arguments.of(IOException(), ErrorTypes.IoException),
            Arguments.of(SQLException(), ErrorTypes.SqlException),
            Arguments.of(JsonParseException(""), ErrorTypes.JsonException),
            Arguments.of(NullPointerException(),ErrorTypes.Generic(null))
        )
    }
}