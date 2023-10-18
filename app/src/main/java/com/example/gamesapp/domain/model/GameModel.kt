package com.example.gamesapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameModel(
    val id : Int,
    val thumbnail: String,
    val title: String,
    val platform: String
) : Parcelable
