package com.shellwoo.kinoguru.feature.login.di

import com.shellwoo.kinoguru.core.di.FragmentScope
import com.shellwoo.kinoguru.feature.login.ui.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface LoginFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginDataModule::class, LoginPresentationModule::class])
    fun injectLoginFragment(): LoginFragment
}