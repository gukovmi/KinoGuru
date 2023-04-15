package com.shellwoo.kinoguru.feature.profile.di

import com.shellwoo.kinoguru.feature.profile.ui.ProfileFragment
import com.shellwoo.kinoguru.shared.user.di.UserComponent
import dagger.Component

@Component(
    dependencies = [ProfileDeps::class, UserComponent::class],
    modules = [ProfilePresentationModule::class]
)
interface ProfileComponent {

    companion object {

        fun create(profileDeps: ProfileDeps): ProfileComponent =
            DaggerProfileComponent.builder()
                .profileDeps(profileDeps)
                .userComponent(UserComponent.create())
                .build()
    }

    fun inject(fragment: ProfileFragment)
}