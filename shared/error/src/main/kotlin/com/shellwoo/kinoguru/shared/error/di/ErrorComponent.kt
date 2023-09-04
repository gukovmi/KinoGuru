package com.shellwoo.kinoguru.shared.error.di

import android.content.Context
import com.shellwoo.kinoguru.shared.error.domain.repository.BaseExceptionRepository
import dagger.Component

@Component(dependencies = [ErrorDeps::class], modules = [ErrorModule::class])
interface ErrorComponent {

    companion object {

        fun create(context: Context): ErrorComponent =
            DaggerErrorComponent.builder()
                .errorDeps(createErrorDeps(context))
                .build()

        private fun createErrorDeps(context: Context): ErrorDeps =
            object : ErrorDeps {
                override val context: Context = context
            }
    }

    val baseExceptionRepository: BaseExceptionRepository
}