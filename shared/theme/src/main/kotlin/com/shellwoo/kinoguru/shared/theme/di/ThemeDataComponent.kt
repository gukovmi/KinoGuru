package com.shellwoo.kinoguru.shared.theme.di

import android.content.Context
import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import dagger.Component

@Component(dependencies = [ThemeDataDeps::class], modules = [ThemeDataModule::class])
interface ThemeDataComponent {

    companion object {

        fun create(context: Context): ThemeDataComponent =
            DaggerThemeDataComponent.builder()
                .themeDataDeps(object : ThemeDataDeps {
                    override val context = context
                })
                .build()
    }

    val themeRepository: ThemeRepository
}