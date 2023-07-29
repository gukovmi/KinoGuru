package com.shellwoo.kinoguru.feature.profile.presentation

import com.shellwoo.kinoguru.shared.language.domain.entity.Language

sealed interface ProfileState {

    object Initial : ProfileState

    object Loading : ProfileState

    data class Content(
        val name: String?,
        val email: String?,
        val photoUrl: String?,
        val language: Language,
    ) : ProfileState
}