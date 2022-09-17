package com.shellwoo.kinoguru.feature.login.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Module
import dagger.Provides

@Module
internal interface LoginDataModule {

    companion object {

        @Provides
        fun provideSignInClient(context: Context): SignInClient = Identity.getSignInClient(context)
    }
}