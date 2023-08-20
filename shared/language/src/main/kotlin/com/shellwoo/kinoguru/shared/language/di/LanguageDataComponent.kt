package com.shellwoo.kinoguru.shared.language.di

import com.shellwoo.kinoguru.shared.language.domain.repository.LanguageRepository
import dagger.Component

@Component(modules = [LanguageDataModule::class])
interface LanguageDataComponent {

    companion object {

        fun create(): LanguageDataComponent = DaggerLanguageDataComponent.create()
    }

    val languageRepository: LanguageRepository
}