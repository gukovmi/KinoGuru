package com.shellwoo.kinoguru.shared.user.domain.usecase

import com.shellwoo.kinoguru.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserPhotoUseCase @Inject constructor(private val userRepository: UserRepository) :
    suspend (String) -> Unit by userRepository::updatePhoto