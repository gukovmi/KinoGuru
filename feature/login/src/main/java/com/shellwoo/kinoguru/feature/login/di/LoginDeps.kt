package com.shellwoo.kinoguru.feature.login.di

import android.content.Context
import com.shellwoo.kinoguru.feature.login.presentation.LoginRouter

interface LoginDeps {

    val context: Context
    val loginRouter: LoginRouter
}