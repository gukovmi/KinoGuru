package com.shellwoo.kinoguru.shared.language.di

import com.shellwoo.kinoguru.shared.language.data.repository.LanguageRepositoryImpl
import com.shellwoo.kinoguru.shared.language.domain.repository.LanguageRepository
import dagger.Binds
import dagger.Module

@Module
interface LanguageDataModule {

    @Binds
    fun bindLanguageRepository(impl: LanguageRepositoryImpl): LanguageRepository
}