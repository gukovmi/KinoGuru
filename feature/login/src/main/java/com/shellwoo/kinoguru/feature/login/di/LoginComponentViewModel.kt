package com.shellwoo.kinoguru.feature.login.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LoginComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component: LoginComponent by lazy {
        LoginComponent.create((app as LoginDepsProvider).loginDeps)
    }
}