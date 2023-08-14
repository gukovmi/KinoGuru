package com.shellwoo.kinoguru.feature.theme.di

import android.content.Context
import com.shellwoo.kinoguru.feature.theme.presentation.ThemeRouter

interface ThemeDeps {

    val context: Context
    val themeRouter: ThemeRouter
}