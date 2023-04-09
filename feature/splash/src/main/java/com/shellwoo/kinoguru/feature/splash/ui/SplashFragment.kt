package com.shellwoo.kinoguru.feature.splash.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.ui.animateOfIncreaseAndDecrease
import com.shellwoo.kinoguru.core.ui.component.BaseDaggerFragment
import com.shellwoo.kinoguru.feature.splash.R
import com.shellwoo.kinoguru.feature.splash.presentation.SplashState
import com.shellwoo.kinoguru.feature.splash.presentation.SplashViewModel
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseDaggerFragment(R.layout.splash_fragment) {

    private companion object {

        const val LOGO_MIN_RELATIVE_SIZE = 0.9f
        const val LOGO_MAX_RELATIVE_SIZE = 1f
        const val LOGO_CYCLE_DURATION_IN_MILLIS = 2000L
    }

    private val viewModel: SplashViewModel by viewModels(factoryProducer = ::viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.start()
    }

    private fun renderState(state: SplashState) {
        when (state) {
            SplashState.Initial -> renderInitialState()
            SplashState.Content -> renderContentState()
        }
    }

    private fun renderInitialState() {
        logo.isVisible = false
    }

    private fun renderContentState() {
        logo.isVisible = true
        logo.animateOfIncreaseAndDecrease(
            minRelativeSize = LOGO_MIN_RELATIVE_SIZE,
            maxRelativeSize = LOGO_MAX_RELATIVE_SIZE,
            cycleDuration = LOGO_CYCLE_DURATION_IN_MILLIS
        )
    }
}