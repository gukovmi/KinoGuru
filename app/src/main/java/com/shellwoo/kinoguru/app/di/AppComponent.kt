package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.app.App
import com.shellwoo.kinoguru.core.di.AppScope
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<App>