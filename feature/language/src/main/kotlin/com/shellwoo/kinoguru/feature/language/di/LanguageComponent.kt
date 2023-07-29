package com.shellwoo.kinoguru.feature.language.di

import dagger.Component

@Component(modules = [LanguagePresentationModule::class], dependencies = [LanguageDeps::class])
interface LanguageComponent {

    companion object {

        fun create(languageDeps: LanguageDeps): LanguageComponent =
            DaggerLanguageComponent.builder()
                .languageDeps(languageDeps)
                .build()
    }

    fun inject(fragment: com.shellwoo.kinoguru.feature.language.ui.LanguageFragment)
}