package com.shellwoo.kinoguru.feature.theme.di

import com.shellwoo.kinoguru.feature.theme.ui.ThemeFragment
import com.shellwoo.kinoguru.shared.theme.di.ThemeDataComponent
import dagger.Component

@Component(
    dependencies = [ThemeDataComponent::class, ThemeDeps::class],
    modules = [ThemePresentationModule::class],
)
interface ThemeComponent {

    companion object {

        fun create(themeDeps: ThemeDeps): ThemeComponent =
            DaggerThemeComponent.builder()
                .themeDeps(themeDeps)
                .themeDataComponent(ThemeDataComponent.create(themeDeps.context))
                .build()
    }

    fun inject(fragment: ThemeFragment)
}