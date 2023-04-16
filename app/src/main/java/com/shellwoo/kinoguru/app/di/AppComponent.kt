package com.shellwoo.kinoguru.app.di

import android.content.Context
import com.shellwoo.kinoguru.feature.login.di.LoginDeps
import com.shellwoo.kinoguru.feature.main.di.MainDeps
import com.shellwoo.kinoguru.feature.profile.di.ProfileDeps
import com.shellwoo.kinoguru.feature.search.di.SearchDeps
import com.shellwoo.kinoguru.feature.splash.di.SplashDeps
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : ProfileDeps, MainDeps, LoginDeps, SearchDeps, SplashDeps, MainActivityDeps {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}