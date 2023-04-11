package com.shellwoo.kinoguru.feature.main.di

import com.shellwoo.kinoguru.feature.main.ui.MainFragment
import dagger.Component

@Component(
    dependencies = [MainDeps::class],
    modules = [MainPresentationModule::class]
)
interface MainComponent {

    fun inject(fragment: MainFragment)
}