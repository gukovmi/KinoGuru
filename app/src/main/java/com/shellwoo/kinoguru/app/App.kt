package com.shellwoo.kinoguru.app

import com.shellwoo.kinoguru.app.di.DaggerAppComponent
import com.shellwoo.kinoguru.app.di.MainActivityDeps
import com.shellwoo.kinoguru.app.di.MainActivityDepsProvider
import com.shellwoo.kinoguru.feature.login.di.LoginDeps
import com.shellwoo.kinoguru.feature.login.di.LoginDepsProvider
import com.shellwoo.kinoguru.feature.main.di.MainDeps
import com.shellwoo.kinoguru.feature.main.di.MainDepsProvider
import com.shellwoo.kinoguru.feature.profile.di.ProfileDeps
import com.shellwoo.kinoguru.feature.profile.di.ProfileDepsProvider
import com.shellwoo.kinoguru.feature.search.di.SearchDeps
import com.shellwoo.kinoguru.feature.search.di.SearchDepsProvider
import com.shellwoo.kinoguru.feature.splash.di.SplashDeps
import com.shellwoo.kinoguru.feature.splash.di.SplashDepsProvider
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication(),
    ProfileDepsProvider, SearchDepsProvider, SplashDepsProvider, LoginDepsProvider, MainDepsProvider, MainActivityDepsProvider {

    private val applicationInjector = DaggerAppComponent.builder()
        .context(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        applicationInjector

    override val mainDeps: MainDeps = applicationInjector
    override val loginDeps: LoginDeps = applicationInjector
    override val profileDeps: ProfileDeps = applicationInjector
    override val splashDeps: SplashDeps = applicationInjector
    override val searchDeps: SearchDeps = applicationInjector
    override val mainActivityDeps: MainActivityDeps = applicationInjector
}