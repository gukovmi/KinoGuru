package com.shellwoo.kinoguru.shared.user.domain.usecase

import com.shellwoo.kinoguru.shared.user.domain.entity.User
import com.shellwoo.kinoguru.shared.user.domain.repository.UserRepository

class GetCurrentUserUseCase(private val userRepository: UserRepository) {

    operator fun invoke(): User? =
        userRepository.get()
}