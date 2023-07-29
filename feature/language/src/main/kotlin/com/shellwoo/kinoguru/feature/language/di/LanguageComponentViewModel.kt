package com.shellwoo.kinoguru.feature.language.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LanguageComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component by lazy { LanguageComponent.create((app as LanguageDepsProvider).languageDeps) }
}