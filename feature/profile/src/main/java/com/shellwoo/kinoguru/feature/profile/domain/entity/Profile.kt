package com.shellwoo.kinoguru.feature.profile.domain.entity

import com.shellwoo.kinoguru.shared.language.domain.entity.Language

data class Profile(
    val name: String?,
    val email: String?,
    val photoUrl: String?,
    val language: Language
)
