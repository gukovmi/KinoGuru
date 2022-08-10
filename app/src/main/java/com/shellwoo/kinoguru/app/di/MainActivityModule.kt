package com.shellwoo.kinoguru.app.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.app.presentation.MainRouter
import com.shellwoo.kinoguru.app.presentation.MainRouterImpl
import com.shellwoo.kinoguru.app.presentation.MainViewModel
import com.shellwoo.kinoguru.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    fun bindMainRouter(impl: MainRouterImpl): MainRouter
}