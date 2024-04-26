package com.shellwoo.kinoguru.shared.user.domain.repository

import com.shellwoo.kinoguru.shared.user.domain.entity.User

interface UserRepository {

    fun get(): User?

    suspend fun updatePhoto(url: String)
}