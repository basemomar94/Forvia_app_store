package com.bassem.forvia_app_store.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bassem.forvia_app_store.data.local.AppDataBase
import com.bassem.forvia_app_store.data.local.AppsDao
import com.bassem.forvia_app_store.presentation.models.AppsUi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AppDaoTest {

    private lateinit var db: AppDataBase
    private lateinit var appDao: AppsDao
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
            .allowMainThreadQueries().build()
        appDao = db.appsDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndReadApp() = runBlocking {
        val appName = "test app"
        val app = AppsUi(
            name = appName,
            downloads = 3,
            iconUrl = "",
            id = 1,
            modifiedAt = "",
            versionCode = 3,
            versionName = "22",
            size = 30,
            storeName = "test",
            rating = 3.3
        )
        appDao.upsertAllApps(listOf(app))
        val apps = appDao.getApps()
        Assert.assertEquals(appName, apps.first().name)
    }

    @Test
    fun insertAndReadMultiApp() = runBlocking {
        val app1 = AppsUi(
            name = "test app 1",
            downloads = 10,
            iconUrl = "",
            id = 1,
            modifiedAt = "",
            versionCode = 1,
            versionName = "1.0",
            size = 10,
            storeName = "store 1",
            rating = 4.0
        )

        val app2 = AppsUi(
            name = "test app 2",
            downloads = 20,
            iconUrl = "",
            id = 2,
            modifiedAt = "",
            versionCode = 2,
            versionName = "2.0",
            size = 30,
            storeName = "store 2",
            rating = 4.5
        )

        val app3 = AppsUi(
            name = "test app 3",
            downloads = 30,
            iconUrl = "",
            id = 3,
            modifiedAt = "",
            versionCode = 3,
            versionName = "3.0",
            size = 30,
            storeName = "store 3",
            rating = 5.0
        )

        appDao.upsertAllApps(listOf(app1, app2, app3))
        val apps = appDao.getApps()
        Assert.assertEquals(10, apps.first().size)
        Assert.assertEquals(3, apps.size)
        Assert.assertEquals(3,apps.last().versionCode)
    }

    @Test
    fun deleteAllData() = runBlocking {
        val app = AppsUi(
            name = "test app",
            downloads = 3,
            iconUrl = "",
            id = 1,
            modifiedAt = "",
            versionCode = 3,
            versionName = "22",
            size = 30,
            storeName = "test",
            rating = 3.3
        )
        appDao.upsertAllApps(listOf(app))
        appDao.deleteAllApps()
        val apps = appDao.getApps()
        Assert.assertEquals(0, apps.size)
    }
}
