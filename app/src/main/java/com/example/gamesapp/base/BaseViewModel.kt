package com.example.gamesapp.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamesapp.common.NavigationCommand
import com.example.gamesapp.common.SingleLiveEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val errorText = SingleLiveEvent<String>()
    val showLoadingProgress = SingleLiveEvent<Boolean>()
    val navigationCommand = SingleLiveEvent<NavigationCommand>()
    val showError = SingleLiveEvent<Boolean>()
}