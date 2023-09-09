package com.shellwoo.kinoguru.shared.error.di

import com.shellwoo.kinoguru.shared.error.domain.repository.BaseExceptionRepository
import dagger.Component

@Component(modules = [ErrorModule::class])
interface ErrorComponent {

    companion object {

        fun create(): ErrorComponent =
            DaggerErrorComponent.builder()
                .build()
    }

    val baseExceptionRepository: BaseExceptionRepository
}