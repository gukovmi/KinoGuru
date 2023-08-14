package com.shellwoo.kinoguru.feature.profile.domain.entity

import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme

data class Profile(
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val language: Language,
    val theme: Theme,
)
