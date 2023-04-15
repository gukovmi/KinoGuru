package com.shellwoo.kinoguru.feature.login.di

import com.shellwoo.kinoguru.core.firebase.di.FirebaseComponent
import com.shellwoo.kinoguru.feature.login.ui.LoginFragment
import dagger.Component

@Component(
    dependencies = [LoginDeps::class, FirebaseComponent::class],
    modules = [LoginPresentationModule::class]
)
interface LoginComponent {

    companion object {

        fun create(loginDeps: LoginDeps): LoginComponent =
            DaggerLoginComponent.builder()
                .loginDeps(loginDeps)
                .firebaseComponent(FirebaseComponent.create())
                .build()
    }

    fun inject(fragment: LoginFragment)
}