package com.example.gamesapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameDb(
    @PrimaryKey
    val id : Int,
    val shortDescription : String,
    val thumbnail: String,
    val genre: String,
    val title: String,
    val platform: String
)