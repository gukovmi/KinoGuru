package com.shellwoo.kinoguru.feature.language.di

import com.shellwoo.kinoguru.feature.language.ui.LanguageFragment
import com.shellwoo.kinoguru.shared.language.di.LanguageDataComponent
import dagger.Component

@Component(
    dependencies = [LanguageDeps::class, LanguageDataComponent::class],
    modules = [LanguagePresentationModule::class],
)
interface LanguageComponent {

    companion object {

        fun create(languageDeps: LanguageDeps): LanguageComponent =
            DaggerLanguageComponent.builder()
                .languageDeps(languageDeps)
                .languageDataComponent(LanguageDataComponent.create())
                .build()
    }

    fun inject(fragment: LanguageFragment)
}