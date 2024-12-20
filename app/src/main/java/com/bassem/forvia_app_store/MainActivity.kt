package com.bassem.forvia_app_store

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bassem.forvia_app_store.databinding.ActivityMainBinding
import com.bassem.forvia_app_store.utils.Logger
import com.bassem.forvia_app_store.utils.createNotificationChannel
import com.bassem.forvia_app_store.utils.hasNotificationPermission
import com.bassem.forvia_app_store.utils.scheduleDailyWork
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val logger = Logger("MainActivity")

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (hasNotificationPermission()) {
            logger.i("App has notification permission")
            scheduleDailyWork()
            createNotificationChannel()
        } else {
            requestNotificationPermission()
        }

    }

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                logger.i("Permission granted")
                scheduleDailyWork()
                createNotificationChannel()
            } else {
                logger.i("Permission denied")
            }
        }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}