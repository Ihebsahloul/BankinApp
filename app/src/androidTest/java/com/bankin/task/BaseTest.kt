package com.bankin.task

import android.os.SystemClock
import com.bankin.task.helpers.CategoriesRequestDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before


open class BaseTest {

    companion object {
        const val SYSTEM_ACTION_INTERVAL_TIME = 2000L
    }

    lateinit var mockWebServer: MockWebServer

    @Before
    open fun setup() {
        mockWebServer = MockWebServer().apply {
            dispatcher = CategoriesRequestDispatcher()
            start(8080)
        }
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    fun sleep() {
        SystemClock.sleep(SYSTEM_ACTION_INTERVAL_TIME)
    }
}