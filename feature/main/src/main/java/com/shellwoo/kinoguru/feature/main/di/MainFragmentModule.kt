package com.shellwoo.kinoguru.feature.main.di

import com.shellwoo.kinoguru.core.di.FragmentScope
import com.shellwoo.kinoguru.feature.main.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainPresentationModule::class])
    fun injectMainFragment(): MainFragment
}