package com.shellwoo.kinoguru.feature.main.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: MainRouter,
) : ViewModel() {

    fun openProfileScreen() {
        router.openProfileScreen()
    }
}