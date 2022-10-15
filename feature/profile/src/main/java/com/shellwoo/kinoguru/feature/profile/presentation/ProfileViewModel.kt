package com.shellwoo.kinoguru.feature.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.core.coroutines.launchTrying
import com.shellwoo.kinoguru.shared.user.domain.entity.User
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val router: ProfileRouter,
) : ViewModel() {

    private val _state = MutableLiveData<ProfileState>(ProfileState.Initial)
    val state: LiveData<ProfileState> = _state

    fun loadInitialData() {
        _state.value = ProfileState.Loading

        viewModelScope.launchTrying(
            errorHandler = {
                handleUserLoadingError()
            },
            block = {
                val user = getCurrentUserUseCase()
                handleCurrentUser(user)
            }
        )
    }

    private fun handleCurrentUser(user: User?) {
        if (user != null) {
            _state.value = ProfileState.Content(
                name = user.name,
                email = user.email,
                photoUrl = user.photoUrl,
            )
        } else {
            router.openLoginScreen()
        }
    }

    private fun handleUserLoadingError() {
        _state.value = ProfileState.InitialDataLoadingError
    }
}