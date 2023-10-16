package com.example.gamesapp

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.gamesapp.worker.GAME_DATA_WORKER
import com.example.gamesapp.worker.GameDataWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class GamesApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayInit()
    }

    private fun delayInit() = applicationScope.launch {
        setupRecurringWorker()
    }

    private fun setupRecurringWorker() {
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.METERED)
            .setRequiresCharging(true)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<GameDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            GAME_DATA_WORKER,
            ExistingPeriodicWorkPolicy.REPLACE,
            repeatingRequest
        )
    }
}