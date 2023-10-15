package com.example.gamesapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GameDb::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract val gameDao : GameDao
}