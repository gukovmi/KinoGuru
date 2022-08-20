package com.shellwoo.kinoguru.feature.login.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.shellwoo.kinoguru.feature.login.data.repository.AuthCredentialRepositoryImpl
import com.shellwoo.kinoguru.feature.login.data.repository.GoogleSignInIntentRepositoryImpl
import com.shellwoo.kinoguru.feature.login.domain.repository.AuthCredentialRepository
import com.shellwoo.kinoguru.feature.login.domain.repository.GoogleSignInIntentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface LoginDataModule {

    companion object {

        @Provides
        fun provideSignInClient(context: Context): SignInClient = Identity.getSignInClient(context)
    }

    @Binds
    fun bindGoogleSignInIntentRepository(impl: GoogleSignInIntentRepositoryImpl): GoogleSignInIntentRepository

    @Binds
    fun bindAuthCredentialRepository(impl: AuthCredentialRepositoryImpl): AuthCredentialRepository
}