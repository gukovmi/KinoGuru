package com.shellwoo.kinoguru.core.ui.component

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BaseFragmentModule {

    @ContributesAndroidInjector
    fun injectBaseFragment(): BaseFragment
}