package com.shellwoo.kinoguru.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.shellwoo.kinoguru.R
import com.shellwoo.kinoguru.app.presentation.MainViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val navigator = AppNavigator(this, R.id.container)

    override fun androidInjector(): AndroidInjector<Any> =
        androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel.setNavBarVisibilityListener(::handleNavBarVisibility)

        if (savedInstanceState == null) {
            viewModel.openSplashScreen()
        }
    }

    private fun handleNavBarVisibility(visible: Any) {
        if (visible !is Boolean) return
        bottomNavigationBar.isVisible = visible
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