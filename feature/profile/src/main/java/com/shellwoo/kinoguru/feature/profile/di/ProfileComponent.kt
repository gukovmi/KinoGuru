package com.shellwoo.kinoguru.feature.profile.di

import com.shellwoo.kinoguru.feature.profile.ui.ProfileFragment
import dagger.Component

@Component(
    dependencies = [ProfileDeps::class],
    modules = [ProfilePresentationModule::class]
)
interface ProfileComponent {

    fun inject(fragment: ProfileFragment)
}