package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.app.navigation.AppCiceroneRouter
import com.shellwoo.kinoguru.app.navigation.AppNavigatorHolder

interface MainActivityDeps {

    val appCiceroneRouter: AppCiceroneRouter
    val appNavigatorHolder: AppNavigatorHolder
}