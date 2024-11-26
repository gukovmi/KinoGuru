package com.shellwoo.kinoguru.feature.splash.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.presentation.observeAsNotNullableState
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.component.NO_CONTENT_LAYOUT_ID
import com.shellwoo.kinoguru.core.ui.ext.createComposeView
import com.shellwoo.kinoguru.feature.splash.di.SplashComponentViewModel
import com.shellwoo.kinoguru.feature.splash.presentation.SplashViewModel

class SplashFragment : BaseFragment(NO_CONTENT_LAYOUT_ID) {

    private val componentViewModel: SplashComponentViewModel by viewModels()

    private val viewModel: SplashViewModel by viewModels(factoryProducer = ::viewModelFactory)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        createComposeView {
            val state by viewModel.state.observeAsNotNullableState()

            SplashScreen(state)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.start()
    }
}