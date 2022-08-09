package com.shellwoo.kinoguru.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private companion object {
        const val DELAY = 2000L
    }

    fun delay() {
        viewModelScope.launch {
            delay(DELAY)
        }
    }
}