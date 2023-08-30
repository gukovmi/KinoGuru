package com.shellwoo.kinoguru.app.di

import android.content.Context
import com.shellwoo.kinoguru.app.App
import com.shellwoo.kinoguru.feature.language.di.LanguageDeps
import com.shellwoo.kinoguru.feature.login.di.LoginDeps
import com.shellwoo.kinoguru.feature.main.di.MainDeps
import com.shellwoo.kinoguru.feature.movie.search.di.MovieSearchDeps
import com.shellwoo.kinoguru.feature.profile.di.ProfileDeps
import com.shellwoo.kinoguru.feature.splash.di.SplashDeps
import com.shellwoo.kinoguru.feature.theme.di.ThemeDeps
import com.shellwoo.kinoguru.shared.theme.di.ThemeDataComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ThemeDataComponent::class], modules = [AppModule::class])
interface AppComponent : ProfileDeps, MainDeps, LanguageDeps, LoginDeps, MovieSearchDeps, SplashDeps, ThemeDeps,
    MainActivityDeps {

    companion object {

        fun create(context: Context): AppComponent = DaggerAppComponent.builder()
            .context(context)
            .themeDataComponent(ThemeDataComponent.create(context))
            .build()
    }

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder

        fun themeDataComponent(themeDataComponent: ThemeDataComponent): Builder
    }

    fun inject(app: App)
}