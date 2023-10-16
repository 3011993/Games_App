package com.example.gamesapp.data.repository

import com.example.gamesapp.common.Resources
import com.example.gamesapp.data.db.GameDao
import com.example.gamesapp.data.db.GameDb
import com.example.gamesapp.data.remote.GamesApi
import com.example.gamesapp.data.remote.dto.toGameDatabase
import com.example.gamesapp.data.remote.dto.toSpecificGameModel
import com.example.gamesapp.domain.model.SpecificGameModel
import com.example.gamesapp.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val dao: GameDao,
    private val api: GamesApi
) : GameRepository {

    override suspend fun getGameList(): Flow<Resources<List<GameDb>>> {
        return flow {
            emit(Resources.Loading<List<GameDb>>())
            try {
                val result = api.getLiveGamesList().toGameDatabase()
                dao.insertAllGames(*result)
                emit(Resources.Success<List<GameDb>>(result.toList()))
            } catch (e: HttpException) {
                emit(Resources.Error<List<GameDb>>(e.message ?: "An unexpected error occurred"))
            } catch (e: Exception) {
                emit(
                    Resources.Error<List<GameDb>>(
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