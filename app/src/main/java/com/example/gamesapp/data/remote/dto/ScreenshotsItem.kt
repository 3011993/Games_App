package com.example.gamesapp.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScreenshotsItem(val image: String = "",
                           val id: Int = 0) : Parcelable