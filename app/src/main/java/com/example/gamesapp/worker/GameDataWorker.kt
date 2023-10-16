package com.example.gamesapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.gamesapp.domain.repository.GameRepository
import retrofit2.HttpException
import javax.inject.Inject


const val GAME_DATA_WORKER = "GameDataWorker"

class GameDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    @Inject
    lateinit var repo: GameRepository
    override suspend fun doWork(): Result {
        return try {
            repo.getGameList()
            Result.success()
        } catch (e: HttpException) {
            Result.failure()
        }
    }
}