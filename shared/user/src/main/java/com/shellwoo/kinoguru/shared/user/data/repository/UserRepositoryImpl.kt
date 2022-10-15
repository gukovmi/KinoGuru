package com.shellwoo.kinoguru.shared.user.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.shellwoo.kinoguru.shared.user.domain.entity.User
import com.shellwoo.kinoguru.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : UserRepository {

    override fun get(): User? {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        return if (firebaseUser != null) {
            User(
                name = firebaseUser.displayName,
                email = firebaseUser.email,
                photoUrl = firebaseUser.photoUrl?.toString(),
            )
        } else {
            null
        }
    }
}