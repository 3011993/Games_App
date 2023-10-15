package com.example.gamesapp.domain.model

data class SpecificGameModel (
    val id : Int,
    val description: String ,
    val title: String ,
    val platform: String ,
    val thumbnail: String,
    val developer: String,
    val genre : String,
    val status: String
)