package com.shellwoo.kinoguru.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    private companion object {
        const val DELAY = 2000L
    }

    fun delay() {
        viewModelScope.launch {
            delay(DELAY)
        }
    }
}