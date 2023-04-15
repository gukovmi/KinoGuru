package com.shellwoo.kinoguru.core.firebase.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Component

@Component(modules = [FirebaseAuthModule::class])
interface FirebaseComponent {

    companion object {

        fun create(): FirebaseComponent = DaggerFirebaseComponent.create()
    }

    fun provideFirebaseAuth(): FirebaseAuth
}