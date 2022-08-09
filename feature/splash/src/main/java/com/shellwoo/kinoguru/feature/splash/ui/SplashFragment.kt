package com.shellwoo.kinoguru.feature.splash.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shellwoo.kinoguru.feature.splash.R
import com.shellwoo.kinoguru.feature.splash.presentation.SplashViewModel

class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val viewModel: SplashViewModel by lazy(::SplashViewModel)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.delay()
    }
}