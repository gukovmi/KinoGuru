package com.shellwoo.kinoguru.app

import com.shellwoo.kinoguru.app.di.DaggerAppComponent
import com.shellwoo.kinoguru.feature.profile.di.ProfileDeps
import com.shellwoo.kinoguru.feature.profile.di.ProfileDepsProvider
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication(), ProfileDepsProvider {

    private val applicationInjector = DaggerAppComponent.builder()
        .context(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        applicationInjector

    override val profileDeps: ProfileDeps = applicationInjector
}