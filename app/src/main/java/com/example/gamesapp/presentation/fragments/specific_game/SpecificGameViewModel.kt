package com.example.gamesapp.presentation.fragments.specific_game

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.gamesapp.base.BaseViewModel
import com.example.gamesapp.common.Constants
import com.example.gamesapp.common.Resources
import com.example.gamesapp.data.remote.GET_SPECIFIC_GAME_QUERY
import com.example.gamesapp.domain.model.SpecificGameModel
import com.example.gamesapp.domain.use_cases.specific_game.GetSpecificGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecificGameViewModel @Inject constructor(
    private val getSpecificGameUseCase: GetSpecificGameUseCase,
    application: Application
) : BaseViewModel(application) {

    private val _specificGame = MutableLiveData<SpecificGameModel>()
    val specificGame: LiveData<SpecificGameModel> = _specificGame

    init {
    }

    fun getSpecificGame(id: String) {
        viewModelScope.launch {
            getSpecificGameUseCase(id).collect{ result ->
                when (result) {
                    is Resources.Success -> {
                        _specificGame.value = result.data!!
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
