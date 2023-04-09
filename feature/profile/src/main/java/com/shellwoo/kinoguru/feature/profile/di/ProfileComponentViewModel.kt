package com.shellwoo.kinoguru.feature.profile.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ProfileComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component: ProfileComponent by lazy {
        DaggerProfileComponent.builder()
            .profileDeps((app as ProfileDepsProvider).profileDeps)
            .build()
    }
}