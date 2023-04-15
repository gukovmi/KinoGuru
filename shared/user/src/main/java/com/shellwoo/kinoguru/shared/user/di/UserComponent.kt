package com.shellwoo.kinoguru.shared.user.di

import com.shellwoo.kinoguru.core.firebase.di.FirebaseComponent
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase
import dagger.Component

@Component(
    dependencies = [FirebaseComponent::class],
    modules = [UserDomainModule::class]
)
interface UserComponent {

    companion object {

        fun create(): UserComponent = DaggerUserComponent.builder()
            .firebaseComponent(FirebaseComponent.create())
            .build()
    }

    fun getCurrentUserUseCase(): GetCurrentUserUseCase
}