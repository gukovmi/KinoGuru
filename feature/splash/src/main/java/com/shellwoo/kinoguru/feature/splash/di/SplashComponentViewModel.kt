package com.shellwoo.kinoguru.feature.splash.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SplashComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component: SplashComponent by lazy {
        SplashComponent.create((app as SplashDepsProvider).splashDeps)
    }
}