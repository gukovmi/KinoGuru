package com.shellwoo.kinoguru.feature.login.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.shellwoo.kinoguru.feature.login.presentation.LoginRouter

interface LoginDeps {

    val loginRouter: LoginRouter
    val firebaseAuth: FirebaseAuth
    val context: Context
}