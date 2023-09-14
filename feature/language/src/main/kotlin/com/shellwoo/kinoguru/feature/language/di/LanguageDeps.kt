package com.shellwoo.kinoguru.feature.language.di

import android.content.Context
import com.shellwoo.kinoguru.feature.language.presentation.LanguageRouter
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetAllLanguagesUseCase

interface LanguageDeps {

    val getAllLanguagesUseCase: GetAllLanguagesUseCase
    val languageRouter: LanguageRouter
    val context: Context
}