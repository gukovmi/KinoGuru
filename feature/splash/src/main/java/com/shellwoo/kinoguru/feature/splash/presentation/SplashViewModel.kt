package com.shellwoo.kinoguru.feature.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val router: SplashRouter,
) : ViewModel() {

    private val _state = MutableLiveData<SplashState>(SplashState.Initial)
    val state: LiveData<SplashState> = _state

    private companion object {

        const val DELAY = 2000L
    }

    fun start() {
        viewModelScope.launch {
            _state.value = SplashState.Content
            delay(DELAY)
            checkCurrentUser()
        }
    }

    private fun checkCurrentUser() {
        val currentUser = getCurrentUserUseCase()
        if (currentUser == null) {
            router.openLoginScreen()
        }
    }
}