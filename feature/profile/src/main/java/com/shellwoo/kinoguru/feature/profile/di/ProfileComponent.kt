package com.shellwoo.kinoguru.feature.profile.di

import com.shellwoo.kinoguru.feature.profile.ui.ProfileFragment
import dagger.Component

@Component(
    dependencies = [ProfileDeps::class],
    modules = [ProfilePresentationModule::class]
)
interface ProfileComponent {

    companion object {

        fun create(profileDeps: ProfileDeps): ProfileComponent =
            DaggerProfileComponent.builder()
                .profileDeps(profileDeps)
                .build()
    }

    fun inject(fragment: ProfileFragment)
}