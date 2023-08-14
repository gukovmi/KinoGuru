package com.shellwoo.kinoguru.feature.profile.presentation

import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme

sealed interface ProfileState {

    object Initial : ProfileState

    object Loading : ProfileState

    data class Content(
        val name: String?,
        val email: String?,
        val photoUrl: String?,
        val language: Language,
        val theme: Theme,
    ) : ProfileState
}