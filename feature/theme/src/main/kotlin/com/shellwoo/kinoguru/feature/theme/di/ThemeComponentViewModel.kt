package com.shellwoo.kinoguru.feature.theme.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ThemeComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component by lazy { ThemeComponent.create((app as ThemeDepsProvider).themeDeps) }
}