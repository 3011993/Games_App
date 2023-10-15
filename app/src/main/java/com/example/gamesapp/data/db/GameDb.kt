package com.example.gamesapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gamesapp.domain.model.GameModel

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

fun GameDb.toGameModel() : GameModel {
     return GameModel(
        id = id,
        title = title,
        thumbnail = thumbnail,
        genre = genre,
        shortDescription = shortDescription,
        platform = platform
    )
}