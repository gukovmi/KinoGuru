package com.shellwoo.kinoguru.shared.language.di

import android.content.Context
import com.shellwoo.kinoguru.shared.language.domain.repository.LanguageRepository
import dagger.Component

@Component(dependencies = [LanguageDataDeps::class], modules = [LanguageDataModule::class])
interface LanguageDataComponent {

    companion object {

        fun create(context: Context): LanguageDataComponent =
            DaggerLanguageDataComponent.builder()
                .languageDataDeps(object : LanguageDataDeps {
                    override val context = context
                })
                .build()
    }

    val languageRepository: LanguageRepository
}