package com.shellwoo.kinoguru.shared.error.di

import com.shellwoo.kinoguru.shared.error.data.repository.BaseExceptionRepositoryImpl
import com.shellwoo.kinoguru.shared.error.domain.repository.BaseExceptionRepository
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverter
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverterImpl
import dagger.Binds
import dagger.Module

@Module
interface ErrorModule {

    @Binds
    fun bindBaseExceptionRepository(impl: BaseExceptionRepositoryImpl): BaseExceptionRepository

    @Binds
    fun bindBaseExceptionMessageConverter(impl: BaseExceptionMessageConverterImpl): BaseExceptionMessageConverter
}