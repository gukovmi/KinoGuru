package com.shellwoo.kinoguru.shared.user.domain.usecase

import com.shellwoo.kinoguru.shared.user.domain.entity.User
import com.shellwoo.kinoguru.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(): User? =
        userRepository.get()
}