package com.example.gamesapp.presentation.fragments.game_list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gamesapp.base.BaseViewModel
import com.example.gamesapp.common.Resources
import com.example.gamesapp.data.db.GameDao
import com.example.gamesapp.data.db.toGameModel
import com.example.gamesapp.domain.model.GameModel
import com.example.gamesapp.domain.use_cases.get_games.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    application: Application,
    private val dao: GameDao,
    private val getGameUseCase: GetGamesUseCase
) :
    BaseViewModel(application) {

    private val _gameList = MutableLiveData<List<GameModel>>()
    val gameList: LiveData<List<GameModel>> = _gameList

    init {
        getGameList()
    }

    private fun getGameList() {
        viewModelScope.launch(Dispatchers.IO) {
            getGameUseCase().collect { result ->
                when (result) {
                    is Resources.Success -> {
                        showLoadingProgress.postValue(false)
                        showError.postValue(false)
                        val data = dao.getAllGames().map { it.toGameModel() }
                        _gameList.postValue(data)
                    }

                    is Resources.Loading -> {
                        showLoadingProgress.postValue(true)
                        showError.postValue(false)
                    }

                    is Resources.Error -> {
                        showLoadingProgress.postValue(false)
                        errorText.postValue(result.message)
                        _gameList.postValue(result.data!!.map { it.toGameModel() })
                    }
                }
            }
        }
    }

    fun filterAllGamesByPlatform(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = dao.filterAllGamesByPlatform(value).map { it.toGameModel() }
            _gameList.postValue(data)
        }
    }
}

enum class PlatformType(val platform: String) {
    PC("PC (Windows)"), BROWSER("Web Browser")
}