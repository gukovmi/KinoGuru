package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.app.ui.MainActivity
import com.shellwoo.kinoguru.core.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun injectMainActivity(): MainActivity
}