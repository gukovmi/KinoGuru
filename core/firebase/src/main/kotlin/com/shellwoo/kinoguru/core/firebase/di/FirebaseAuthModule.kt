package com.shellwoo.kinoguru.core.firebase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides

@Module
class FirebaseAuthModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
}