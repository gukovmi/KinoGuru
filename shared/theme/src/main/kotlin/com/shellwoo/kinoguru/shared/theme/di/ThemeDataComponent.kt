package com.shellwoo.kinoguru.shared.theme.di

import android.content.Context
import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Builder

@Component(modules = [ThemeDataModule::class])
interface ThemeDataComponent {

    companion object {

        fun create(context: Context): ThemeDataComponent =
            DaggerThemeDataComponent.builder()
                .context(context)
                .build()
    }

    @Component.Builder
    interface Builder {

        fun build(): ThemeDataComponent

        @BindsInstance
        fun context(context: Context): Builder
    }

    val themeRepository: ThemeRepository
}