package com.shellwoo.kinoguru.app

import android.app.Application
import com.shellwoo.kinoguru.app.di.AppComponent
import com.shellwoo.kinoguru.app.di.DaggerAppComponent
import com.shellwoo.kinoguru.app.di.MainActivityDeps
import com.shellwoo.kinoguru.app.di.MainActivityDepsProvider
import com.shellwoo.kinoguru.feature.language.di.LanguageDeps
import com.shellwoo.kinoguru.feature.language.di.LanguageDepsProvider
import com.shellwoo.kinoguru.feature.login.di.LoginDeps
import com.shellwoo.kinoguru.feature.login.di.LoginDepsProvider
import com.shellwoo.kinoguru.feature.main.di.MainDeps
import com.shellwoo.kinoguru.feature.main.di.MainDepsProvider
import com.shellwoo.kinoguru.feature.notification.NotificationChannelFactory
import com.shellwoo.kinoguru.feature.profile.di.ProfileDeps
import com.shellwoo.kinoguru.feature.profile.di.ProfileDepsProvider
import com.shellwoo.kinoguru.feature.search.di.SearchDeps
import com.shellwoo.kinoguru.feature.search.di.SearchDepsProvider
import com.shellwoo.kinoguru.feature.splash.di.SplashDeps
import com.shellwoo.kinoguru.feature.splash.di.SplashDepsProvider

class App : Application(),
    ProfileDepsProvider, SearchDepsProvider, SplashDepsProvider, LanguageDepsProvider, LoginDepsProvider, MainDepsProvider,
    MainActivityDepsProvider {

    private val appComponent: AppComponent = DaggerAppComponent.builder()
        .context(this)
        .build()

    override fun onCreate() {
        super.onCreate()

        NotificationChannelFactory(this).create()
    }

    override val mainDeps: MainDeps = appComponent
    override val languageDeps: LanguageDeps = appComponent
    override val loginDeps: LoginDeps = appComponent
    override val profileDeps: ProfileDeps = appComponent
    override val splashDeps: SplashDeps = appComponent
    override val searchDeps: SearchDeps = appComponent
    override val mainActivityDeps: MainActivityDeps = appComponent
}