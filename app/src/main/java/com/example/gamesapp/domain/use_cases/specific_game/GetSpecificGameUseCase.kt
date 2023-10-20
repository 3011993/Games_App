package com.example.gamesapp.domain.use_cases.specific_game

import com.example.gamesapp.common.Resources
import com.example.gamesapp.domain.model.SpecificGameModel
import com.example.gamesapp.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecificGameUseCase @Inject constructor(private val repository: GameRepository) {

    suspend operator fun invoke(gameId : String) : Flow<Resources<SpecificGameModel>> =
        repository.getSpecificGame(gameId)
}