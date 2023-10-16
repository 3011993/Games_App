package com.example.gamesapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import okhttp3.internal.platform.Platform

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGames(vararg gameDb: GameDb)

    @Query("SELECT * FROM GameDb")
    fun getAllGames(): List<GameDb>

    @Query("SELECT * FROM GameDb WHERE platform =:platform")
    fun filterAllGamesByPlatform(platform: String): List<GameDb>

}