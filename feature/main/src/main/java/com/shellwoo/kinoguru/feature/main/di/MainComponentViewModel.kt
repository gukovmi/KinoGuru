package com.shellwoo.kinoguru.feature.main.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component: MainComponent by lazy {
        DaggerMainComponent.builder()
            .mainDeps((app as MainDepsProvider).mainDeps)
            .build()
    }
}