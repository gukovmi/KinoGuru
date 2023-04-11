package com.shellwoo.kinoguru.feature.main.di

import com.shellwoo.kinoguru.core.navigation.NavigatorFactory
import com.shellwoo.kinoguru.feature.main.navigation.MainNavigatorHolder
import com.shellwoo.kinoguru.feature.main.presentation.MainRouter

interface MainDeps {

    val router: MainRouter
    val navigatorHolder: MainNavigatorHolder
    val navigatorFactory: NavigatorFactory
}