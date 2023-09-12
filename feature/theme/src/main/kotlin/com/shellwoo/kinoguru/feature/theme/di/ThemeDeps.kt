package com.shellwoo.kinoguru.feature.theme.di

import android.content.Context
import com.shellwoo.kinoguru.feature.theme.presentation.ThemeRouter
import com.shellwoo.kinoguru.shared.theme.domain.usecase.GetAvailableThemesUseCase

interface ThemeDeps {

    val getAvailableThemesUseCase: GetAvailableThemesUseCase
    val themeRouter: ThemeRouter
    val context: Context
}