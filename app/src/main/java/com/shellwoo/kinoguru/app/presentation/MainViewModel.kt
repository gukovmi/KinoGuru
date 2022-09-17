package com.shellwoo.kinoguru.app.presentation

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.ResultListener
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: MainRouter,
) : ViewModel() {

    fun setNavBarVisibilityListener(listener: ResultListener) {
        router.setNavBarVisibilityListener(listener)
    }

    fun openSplashScreen() {
        router.openSplashScreen()
    }
}