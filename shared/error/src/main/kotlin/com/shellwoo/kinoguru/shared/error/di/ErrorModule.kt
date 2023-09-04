package com.shellwoo.kinoguru.shared.error.di

import com.shellwoo.kinoguru.shared.error.data.repository.BaseExceptionRepositoryImpl
import com.shellwoo.kinoguru.shared.error.domain.repository.BaseExceptionRepository
import dagger.Binds
import dagger.Module

@Module
interface ErrorModule {

    @Binds
    fun BaseExceptionRepositoryImpl.bind(): BaseExceptionRepository
}