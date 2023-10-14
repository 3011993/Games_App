package com.example.gamesapp.data.remote.dto

import com.example.gamesapp.domain.model.GameModel
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

fun GameDto.toGameModel(): GameModel {
    return GameModel(
        id = id,
        title = title,
        thumbnail = thumbnail,
        genre = genre,
        shortDescription = shortDescription,
        platform = platform
    )
}