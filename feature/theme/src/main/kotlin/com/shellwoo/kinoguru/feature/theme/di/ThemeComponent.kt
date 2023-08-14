package com.shellwoo.kinoguru.feature.theme.di

import com.shellwoo.kinoguru.feature.theme.ui.ThemeFragment
import dagger.Component

@Component(modules = [ThemePresentationModule::class], dependencies = [ThemeDeps::class])
interface ThemeComponent {

    companion object {

        fun create(themeDeps: ThemeDeps): ThemeComponent =
            DaggerThemeComponent.builder()
                .themeDeps(themeDeps)
                .build()
    }

    fun inject(fragment: ThemeFragment)
}