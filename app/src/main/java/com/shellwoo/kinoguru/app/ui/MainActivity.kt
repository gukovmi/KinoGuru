package com.shellwoo.kinoguru.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shellwoo.kinoguru.app.R
import com.shellwoo.kinoguru.app.navigation.AppNavigatorHolder
import com.shellwoo.kinoguru.app.presentation.MainComponentViewModel
import com.shellwoo.kinoguru.app.presentation.MainViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactory
import com.shellwoo.kinoguru.core.navigation.NavigatorFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigatorHolder: AppNavigatorHolder

    @Inject
    lateinit var navigatorFactory: NavigatorFactory

    private val componentViewModel: MainComponentViewModel by viewModels()
    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val navigator by lazy { navigatorFactory.create(this, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        componentViewModel.component.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.openSplashScreen()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.holder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.holder.removeNavigator()
        super.onPause()
    }
}