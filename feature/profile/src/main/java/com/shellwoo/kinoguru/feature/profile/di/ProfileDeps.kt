package com.shellwoo.kinoguru.feature.profile.di

import com.shellwoo.kinoguru.feature.profile.presentation.ProfileRouter
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase

interface ProfileDeps {

    val getCurrentUserUseCase: GetCurrentUserUseCase
    val profileRouter: ProfileRouter
}