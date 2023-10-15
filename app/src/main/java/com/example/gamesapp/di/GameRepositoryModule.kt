package com.example.gamesapp.di

import com.example.gamesapp.data.db.GameDao
import com.example.gamesapp.data.remote.GamesApi
import com.example.gamesapp.data.repository.GameRepositoryImpl
import com.example.gamesapp.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GameRepositoryModule {


    @Provides
    @Singleton
    fun getGameRepository(dao : GameDao,api: GamesApi): GameRepository {
        return GameRepositoryImpl(dao,api)

    }


}