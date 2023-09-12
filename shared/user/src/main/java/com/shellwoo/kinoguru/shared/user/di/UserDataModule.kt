package com.shellwoo.kinoguru.shared.user.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shellwoo.kinoguru.shared.user.data.repository.UserRepositoryImpl
import com.shellwoo.kinoguru.shared.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UserDataModule {

    companion object {

        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
    }

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}