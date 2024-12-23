package com.bassem.forvia_app_store.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.bassem.forvia_app_store.R
import com.bassem.forvia_app_store.worker.NewAppsWorker
import java.util.concurrent.TimeUnit

fun Context.createNotificationChannel() {
    val channelId = getString(R.string.default_notification_channel_id)
    val channelName = getString(R.string.default_notification_channel_name)

    val notificationManager = getSystemService(NotificationManager::class.java)
    if (notificationManager != null) {
        val existingChannel = notificationManager.getNotificationChannel(channelId)
        if (existingChannel == null) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = getString(R.string.news_app_alert)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}


@SuppressLint("MissingPermission", "NotificationPermission")
fun Context.sendBackgroundNotification() {
    val notification = NotificationCompat.Builder(
        this,
        getString(R.string.default_notification_channel_id)
    )
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle(getRandomNotification().first)
        .setOnlyAlertOnce(true)
        .setOngoing(false)
        .setContentText(getRandomNotification().second)
        .setAutoCancel(false)
        .build()
    val notificationManger = NotificationManagerCompat.from(this)
    notificationManger.notify(1, notification)
}


fun Context.hasNotificationPermission(): Boolean {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}

fun Context.scheduleDailyWork() {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
    val workRequest =
        PeriodicWorkRequestBuilder<NewAppsWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()
    val workManager = WorkManager.getInstance(this)
    workManager.enqueueUniquePeriodicWork(
        "NewAppsWorker",
        ExistingPeriodicWorkPolicy.UPDATE,
        workRequest
    )
}


fun Context.getRandomNotification(): Pair<String, String> {
    val marketingMap = mapOf(
        getString(R.string.notification_title) to getString(R.string.notification_text),
        getString(R.string.notification_title1) to getString(R.string.notification_text1),
        getString(R.string.notification_title2) to getString(R.string.notification_text2),
        getString(R.string.notification_title3) to getString(R.string.notification_text3),
        getString(R.string.notification_title4) to getString(R.string.notification_text4),
        getString(R.string.notification_title5) to getString(R.string.notification_text5),
        getString(R.string.notification_title6) to getString(R.string.notification_text6),
        getString(R.string.notification_title7) to getString(R.string.notification_text7),
        getString(R.string.notification_title8) to getString(R.string.notification_text8),
        getString(R.string.notification_title9) to getString(R.string.notification_text9),
        getString(R.string.notification_title10) to getString(R.string.notification_text10),
    )
    val randomEntry = marketingMap.entries.random()
    return Pair(randomEntry.key, randomEntry.value)
}