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
import com.shellwoo.kinoguru.feature.notification.NotificationChannelInitializer
import com.shellwoo.kinoguru.feature.profile.di.ProfileDeps
import com.shellwoo.kinoguru.feature.profile.di.ProfileDepsProvider
import com.shellwoo.kinoguru.feature.search.di.SearchDeps
import com.shellwoo.kinoguru.feature.search.di.SearchDepsProvider
import com.shellwoo.kinoguru.feature.splash.di.SplashDeps
import com.shellwoo.kinoguru.feature.splash.di.SplashDepsProvider
import com.shellwoo.kinoguru.feature.theme.di.ThemeDeps
import com.shellwoo.kinoguru.feature.theme.di.ThemeDepsProvider
import com.shellwoo.kinoguru.shared.theme.domain.usecase.InitThemeUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class App : Application(),
    ProfileDepsProvider, SearchDepsProvider, SplashDepsProvider, LanguageDepsProvider, LoginDepsProvider, ThemeDepsProvider,
    MainDepsProvider, MainActivityDepsProvider {

    @Inject
    lateinit var notificationChannelInitializer: NotificationChannelInitializer

    @Inject
    lateinit var initThemeUseCase: InitThemeUseCase

    private val mainScope = MainScope()

    private val appComponent: AppComponent = DaggerAppComponent.builder()
        .context(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        mainScope.launch {
            initThemeUseCase()
            notificationChannelInitializer()
        }
    }

    override val mainDeps: MainDeps = appComponent
    override val languageDeps: LanguageDeps = appComponent
    override val loginDeps: LoginDeps = appComponent
    override val profileDeps: ProfileDeps = appComponent
    override val splashDeps: SplashDeps = appComponent
    override val searchDeps: SearchDeps = appComponent
    override val mainActivityDeps: MainActivityDeps = appComponent
    override val themeDeps: ThemeDeps = appComponent
}