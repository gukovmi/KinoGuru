package com.shellwoo.kinoguru.shared.user.di

import com.shellwoo.kinoguru.shared.user.data.repository.UserRepositoryImpl
import com.shellwoo.kinoguru.shared.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface UserDataModule {

    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}