package com.shellwoo.kinoguru.feature.search.di

import com.shellwoo.kinoguru.core.di.FragmentScope
import com.shellwoo.kinoguru.feature.search.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SearchFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [
        SearchDataModule::class,
        SearchPresentationModule::class,
    ])
    fun injectSearchFragment(): SearchFragment
}