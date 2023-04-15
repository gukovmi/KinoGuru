package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.app.ui.MainActivity
import dagger.Component

@Component(
    dependencies = [MainActivityDeps::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent {

    companion object {

        fun create(mainActivityDeps: MainActivityDeps): MainActivityComponent =
            DaggerMainActivityComponent.builder()
                .mainActivityDeps(mainActivityDeps)
                .build()
    }

    fun inject(activity: MainActivity)
}
