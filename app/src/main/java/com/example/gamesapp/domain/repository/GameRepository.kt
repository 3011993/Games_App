package com.example.gamesapp.domain.repository

import com.example.gamesapp.common.Resources
import com.example.gamesapp.data.db.GameDb

import com.example.gamesapp.domain.model.SpecificGameModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    suspend fun getGameList () : Flow<Resources<List<GameDb>>>

    suspend fun getSpecificGame (gameId : String) : Flow<Resources<SpecificGameModel>>


}