package com.bassem.forvia_app_store.presentation.utils

import android.content.Context
import android.view.View
import com.bassem.forvia_app_store.BaseTest
import com.bassem.forvia_app_store.R
import com.bassem.forvia_app_store.data.models.ErrorTypes
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.stream.Stream

class UiExtensionTest : BaseTest() {

    @Test
    fun `gone should set view visibility to GONE`() {
        val view = mockk<View>(relaxed = true)

        view.gone()

        verify { view.visibility = View.GONE }
    }

    @Test
    fun `visible should set view visibility to Visible`() {
        val view = mockk<View>(relaxed = true)

        view.visible()

        verify { view.visibility = View.VISIBLE }
    }

    @Test
    fun `formatToLocalDate should return formatted date`() {
        val dateString = "2024-12-23 14:30:00"
        val expectedOutput = SimpleDateFormat.getDateTimeInstance(
            SimpleDateFormat.LONG,
            SimpleDateFormat.SHORT,
            Locale.getDefault()
        ).format(SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(dateString)!!)

        assertEquals(expectedOutput, formatToLocalDate(dateString))
    }

    @Test
    fun `formatToLocalDate should return Invalid Date for incorrect input`() {
        val invalidDateString = "invalid-date"

        assertEquals("Invalid Date", formatToLocalDate(invalidDateString))
    }

    @ParameterizedTest
    @MethodSource("provideErrorMessages")
    fun `getErrorMessage should return correct error messages`(
        errorType: ErrorTypes?,
        expectedMessage: String
    ) {
        val context = mockk<Context>(relaxed = true)

        every { context.getString(R.string.unexpected_error) } returns "Unexpected error occurred"
        every { context.getString(R.string.net_work_error) } returns "Network error"
        every { context.getString(R.string.local_parsing_error) } returns "Local parsing error"
        every { context.getString(R.string.remote_parsing_error) } returns "Remote parsing error"

        assertEquals(expectedMessage, context.getErrorMessage(errorType))
    }

    companion object {
        @JvmStatic
        fun provideErrorMessages(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(null, "Unexpected error occurred"),
                Arguments.of(ErrorTypes.Generic(null), "Unexpected error occurred"),
                Arguments.of(ErrorTypes.Generic("Custom error"), "Custom error"),
                Arguments.of(ErrorTypes.IoException, "Network error"),
                Arguments.of(ErrorTypes.JsonException, "Local parsing error"),
                Arguments.of(ErrorTypes.SqlException, "Remote parsing error")
            )
        }
    }
}