package com.shellwoo.kinoguru.feature.profile.di

import com.shellwoo.kinoguru.feature.profile.ui.ProfileFragment
import com.shellwoo.kinoguru.shared.language.di.LanguageDataComponent
import dagger.Component

@Component(
    dependencies = [ProfileDeps::class, LanguageDataComponent::class],
    modules = [ProfilePresentationModule::class]
)
interface ProfileComponent {

    companion object {

        fun create(profileDeps: ProfileDeps): ProfileComponent =
            DaggerProfileComponent.builder()
                .profileDeps(profileDeps)
                .languageDataComponent(LanguageDataComponent.create(profileDeps.context))
                .build()
    }

    fun inject(fragment: ProfileFragment)
}