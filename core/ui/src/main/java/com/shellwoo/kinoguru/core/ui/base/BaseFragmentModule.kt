package com.shellwoo.kinoguru.core.ui.base

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BaseFragmentModule {

    @ContributesAndroidInjector
    fun injectBaseFragment(): BaseFragment
}