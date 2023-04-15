package com.shellwoo.kinoguru.feature.login.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.feature.login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [LoginDataModule::class, ViewModelFactoryModule::class])
internal interface LoginPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindViewModel(viewModel: LoginViewModel): ViewModel
}