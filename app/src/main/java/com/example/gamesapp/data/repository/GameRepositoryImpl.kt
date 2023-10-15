package com.example.gamesapp.data.repository

import com.example.gamesapp.common.Resources
import com.example.gamesapp.data.remote.GamesApi
import com.example.gamesapp.data.remote.dto.toGameModel
import com.example.gamesapp.data.remote.dto.toSpecificGameModel
import com.example.gamesapp.domain.model.GameModel
import com.example.gamesapp.domain.model.SpecificGameModel
import com.example.gamesapp.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GamesApi
) : GameRepository {

    override suspend fun getGame(): Flow<Resources<List<GameModel>>> {
        return flow {
            emit(Resources.Loading<List<GameModel>>())
            try {
                val result = api.getLiveGamesList().map { it.toGameModel() }
                emit(Resources.Success<List<GameModel>>(result))
            } catch (e: HttpException) {
                emit(Resources.Error<List<GameModel>>(e.message ?: "An unexpected error occurred"))

            } catch (e: Exception) {
                emit(
                    Resources.Error<List<GameModel>>(
                        "couldn't reach to the server,please check your connection"
                    )
                )
            }
        }

    }

    override suspend fun getSpecificGame(gameId: String): Flow<Resources<SpecificGameModel>> {
        return flow {
            emit(Resources.Loading<SpecificGameModel>())
            try {
                val result = api.getGameById(gameId).toSpecificGameModel()
                emit(Resources.Success<SpecificGameModel>(result))
            } catch (e: HttpException) {
                emit(
                    Resources.Error<SpecificGameModel>(
                        e.message ?: "An unexpected error occurred"
                    )
                )

            } catch (e: Exception) {
                emit(
                    Resources.Error<SpecificGameModel>(
                        "couldn't reach to the server,please check your connection"
                    )
                )
            }
        }
    }
}