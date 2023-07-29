package com.shellwoo.kinoguru.feature.language.di

import android.content.Context
import com.shellwoo.kinoguru.feature.language.presentation.LanguageRouter

interface LanguageDeps {

    val context: Context
    val languageRouter: LanguageRouter
}