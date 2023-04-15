package com.shellwoo.kinoguru.app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shellwoo.kinoguru.app.di.MainActivityComponent
import com.shellwoo.kinoguru.app.di.MainActivityDepsProvider

class MainComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component: MainActivityComponent by lazy {
        MainActivityComponent.create((app as MainActivityDepsProvider).mainActivityDeps)
    }
}