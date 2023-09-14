package com.shellwoo.kinoguru.feature.language.di

import com.shellwoo.kinoguru.feature.language.ui.LanguageFragment
import dagger.Component

@Component(
    dependencies = [LanguageDeps::class],
    modules = [LanguagePresentationModule::class],
)
interface LanguageComponent {

    companion object {

        fun create(languageDeps: LanguageDeps): LanguageComponent =
            DaggerLanguageComponent.builder()
                .languageDeps(languageDeps)
                .build()
    }

    fun inject(fragment: LanguageFragment)
}