package com.example.gamesapp.data.remote.dto

import com.example.gamesapp.domain.model.SpecificGameModel
import com.google.gson.annotations.SerializedName

data class SpecificGameDto(
    val shortDescription: String,
    val minimumSystemRequirements: MinimumSystemRequirements,
    val thumbnail: String,
    @SerializedName("freetogameProfileUrl") val gameProfileUrl: String,
    val description: String,
    val title: String,
    val platform: String,
    val screenshots: List<ScreenshotsItem>?,
    val gameUrl: String,
    val releaseDate: String,
    val genre: String,
    val publisher: String,
    val developer: String,
    val id: Int,
    val status: String
)

fun SpecificGameDto.toSpecificGameModel(): SpecificGameModel {
    return SpecificGameModel(
        id = id,
        description = description,
        developer = developer,
        platform = platform,
        title = title,
        genre = genre,
        thumbnail = thumbnail
    )
}