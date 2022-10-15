package com.shellwoo.kinoguru.feature.profile.di

import com.shellwoo.kinoguru.feature.profile.ui.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ProfileFragmentModule {

    @ContributesAndroidInjector(modules = [ProfilePresentationModule::class])
    fun injectProfileFragment(): ProfileFragment
}