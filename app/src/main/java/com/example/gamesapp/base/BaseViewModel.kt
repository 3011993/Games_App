package com.example.gamesapp.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.gamesapp.common.NavigationCommand
import com.example.gamesapp.common.SingleLiveEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val showErrorToast = SingleLiveEvent<String>()
    val showLoadingProgress = SingleLiveEvent<Boolean>()
    val navigationCommand = SingleLiveEvent<NavigationCommand>()



}