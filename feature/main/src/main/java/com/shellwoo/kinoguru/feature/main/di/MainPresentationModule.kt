package com.shellwoo.kinoguru.feature.main.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.feature.main.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface MainPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}