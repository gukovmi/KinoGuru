package com.shellwoo.kinoguru.feature.splash.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.ui.base.BaseFragment
import com.shellwoo.kinoguru.feature.splash.R
import com.shellwoo.kinoguru.feature.splash.presentation.SplashViewModel

class SplashFragment : BaseFragment(R.layout.splash_fragment) {

    private val viewModel: SplashViewModel by viewModels(factoryProducer = ::viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.delay()
    }
}