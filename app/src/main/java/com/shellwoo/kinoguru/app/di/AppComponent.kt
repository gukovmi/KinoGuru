package com.shellwoo.kinoguru.app.di

import android.content.Context
import com.shellwoo.kinoguru.app.App
import com.shellwoo.kinoguru.core.di.AppScope
import com.shellwoo.kinoguru.feature.profile.di.ProfileDeps
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<App>, ProfileDeps {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}