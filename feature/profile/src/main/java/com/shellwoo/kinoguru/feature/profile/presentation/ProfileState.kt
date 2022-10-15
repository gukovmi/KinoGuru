package com.shellwoo.kinoguru.feature.profile.presentation

sealed interface ProfileState {

    object Initial : ProfileState

    object Loading : ProfileState

    data class Content(
        val name: String?,
        val email: String?,
        val photoUrl: String?,
    ) : ProfileState

    object InitialDataLoadingError : ProfileState
}