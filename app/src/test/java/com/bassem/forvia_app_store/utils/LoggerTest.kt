package com.bassem.forvia_app_store.utils

import android.util.Log
import com.bassem.forvia_app_store.BaseTest
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Test

class LoggerTest : BaseTest() {

    private val message = "test"
    private val tag = this::class.java.simpleName
    private lateinit var logger: Logger

    override fun setup() {
        super.setup()
        mockkStatic(Log::class)
        logger = Logger(tag)
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        every { Log.v(any(), any()) } returns 0
    }

    @Test
    fun `test debug log`() {
        logger.d(message)
        verify { Log.d(tag, message) }
    }

    @Test
    fun `test info log`() {
        logger.i(message)
        verify { Log.i(tag, message) }
    }

    @Test
    fun `test logging is disabled`() {
        logger = Logger(tag, isEnabled = false)
        logger.d(message)
        verify(exactly = 0) { Log.d(tag, message) }
    }

    @Test
    fun `test error log with throwable`() {
        val throwable = Throwable("Test throwable")
        logger.e(message, throwable)
        verify { Log.e(tag, message, throwable) }
    }
}
