package com.shellwoo.kinoguru.feature.splash.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.viewmodel.ViewModelFactory
import com.shellwoo.kinoguru.feature.splash.R
import com.shellwoo.kinoguru.feature.splash.presentation.SplashViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashFragment : DaggerFragment(R.layout.splash_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SplashViewModel by viewModels(factoryProducer = ::viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.delay()
    }
}