package com.shellwoo.kinoguru.app.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: MainRouter,
) : ViewModel() {

    fun openSplashScreen() {
        router.openSplashScreen()
    }
}