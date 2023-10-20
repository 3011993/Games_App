package com.example.gamesapp.di

import android.content.Context
import androidx.room.Room
import com.example.gamesapp.data.db.GameDao
import com.example.gamesapp.data.db.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun getGameDatabase(@ApplicationContext context: Context): GameDatabase {
        return Room.databaseBuilder(
            context, GameDatabase::class.java, "game_database"
        ).build()
    }

    @Provides
    @Singleton
    fun getGameDao(database: GameDatabase): GameDao {
        return database.gameDao
    }
}