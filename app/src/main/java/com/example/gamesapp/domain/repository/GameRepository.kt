package com.example.gamesapp.domain.repository

import com.example.gamesapp.common.Resources

import com.example.gamesapp.domain.model.GameModel
import com.example.gamesapp.domain.model.SpecificGameModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    suspend fun getGame () : Flow<Resources<List<GameModel>>>

    suspend fun getSpecificGame (gameId : String) : Flow<Resources<SpecificGameModel>>


}