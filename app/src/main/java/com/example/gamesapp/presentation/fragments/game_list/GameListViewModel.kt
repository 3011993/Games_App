package com.example.gamesapp.presentation.fragments.game_list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gamesapp.base.BaseViewModel
import com.example.gamesapp.common.Resources
import com.example.gamesapp.domain.model.GameModel
import com.example.gamesapp.domain.repository.GameRepository
import com.example.gamesapp.domain.use_cases.get_games.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    application: Application,
    private val getGameUseCase: GetGamesUseCase
) :
    BaseViewModel(application) {

    private val _gameList = MutableLiveData<List<GameModel>>()
    val gameList: LiveData<List<GameModel>> = _gameList

    init {
        getGameList()
    }

    private fun getGameList() {
        viewModelScope.launch {
            getGameUseCase().collect { result ->
                when (result) {
                    is Resources.Success -> {
                        _gameList.value = result.data ?: emptyList()
                    }

                    is Resources.Loading -> {
                        showLoadingProgress.value = true
                    }

                    is Resources.Error -> {
                        showErrorToast.value = result.message
                    }
                }

            }

        }
    }
}