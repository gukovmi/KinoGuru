package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.feature.login.di.LoginFragmentModule
import com.shellwoo.kinoguru.feature.main.di.MainFragmentModule
import com.shellwoo.kinoguru.feature.search.di.SearchFragmentModule
import com.shellwoo.kinoguru.feature.splash.di.SplashFragmentModule
import dagger.Module

@Module(
    includes = [
        LoginFragmentModule::class,
        MainFragmentModule::class,
        SearchFragmentModule::class,
        SplashFragmentModule::class,
    ]
)
interface FragmentModule