package com.example.gamesapp.domain.use_cases.get_games

import com.example.gamesapp.common.Resources
import com.example.gamesapp.data.db.GameDb
import com.example.gamesapp.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(private val repository: GameRepository) {

    suspend operator fun invoke() : Flow<Resources<List<GameDb>>> = repository.getGameList()
}