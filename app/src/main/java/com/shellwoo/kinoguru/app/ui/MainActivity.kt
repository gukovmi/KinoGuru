package com.shellwoo.kinoguru.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.shellwoo.kinoguru.R
import com.shellwoo.kinoguru.feature.splash.SplashDestination
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val cicerone = Cicerone.create()
    private val navigatorHolder: NavigatorHolder = cicerone.getNavigatorHolder()
    private val router: Router = cicerone.router
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openSplashFragment()
        }
    }

    private fun openSplashFragment() {
        router.newRootScreen(SplashDestination)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}