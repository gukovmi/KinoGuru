package com.shellwoo.kinoguru.feature.profile.di

import android.content.Context
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileRouter
import com.shellwoo.kinoguru.shared.theme.domain.usecase.GetCurrentThemeUseCase
import com.shellwoo.kinoguru.shared.theme.domain.usecase.SetCurrentThemeUseCase

interface ProfileDeps {

    val getCurrentThemeUseCase: GetCurrentThemeUseCase
    val setCurrentThemeUseCase: SetCurrentThemeUseCase
    val profileRouter: ProfileRouter
    val context: Context
}