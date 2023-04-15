package com.shellwoo.kinoguru.shared.user.di

import com.shellwoo.kinoguru.shared.user.domain.repository.UserRepository
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [UserDataModule::class])
class UserDomainModule {

    @Provides
    fun provideGetCurrentUserUseCase(repository: UserRepository): GetCurrentUserUseCase =
        GetCurrentUserUseCase(repository)
}