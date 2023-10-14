package com.example.gamesapp.data.remote

import com.example.gamesapp.data.remote.dto.GameDto
import com.example.gamesapp.data.remote.dto.SpecificGameDto
import retrofit2.http.GET
import retrofit2.http.Path

const val GET_GAME_QUERY = "api/games"
const val GET_SPECIFIC_GAME_QUERY = "api/games/{id}"


interface GamesApi {

    @GET(GET_GAME_QUERY)
    suspend fun getLiveGamesList(): List<GameDto>

    @GET(GET_SPECIFIC_GAME_QUERY)
    suspend fun getDetailsFromSpecificGame(@Path("id") gameId: Int): SpecificGameDto
}