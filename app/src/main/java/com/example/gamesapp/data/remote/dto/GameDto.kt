package com.example.gamesapp.data.remote.dto

import com.example.gamesapp.data.db.GameDb
import com.google.gson.annotations.SerializedName

data class GameDto(
    @SerializedName("short_description") val shortDescription: String,
    val thumbnail: String,
    @SerializedName("game_url") val gameUrl: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("freetogame_profile_url") val gameProfileUrl: String,
    val genre: String,
    val publisher: String,
    val developer: String,
    val id: Int,
    val title: String,
    val platform: String
)

fun List<GameDto>.toGameDatabase(): Array<GameDb> {
    return map {
        GameDb(
            id = it.id,
            title = it.title,
            thumbnail = it.thumbnail,
            genre = it.genre,
            shortDescription = it.shortDescription,
            platform = it.platform
        )
    }.toTypedArray()
}