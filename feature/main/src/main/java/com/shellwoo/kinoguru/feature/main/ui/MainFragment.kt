package com.shellwoo.kinoguru.feature.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.shellwoo.kinoguru.core.navigation.di.MainNavigation
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.feature.main.R
import com.shellwoo.kinoguru.feature.main.presentation.MainViewModel
import javax.inject.Inject

class MainFragment : BaseFragment(R.layout.main_fragment) {

    @MainNavigation
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    lateinit var navigator: AppNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = AppNavigator(requireActivity(), R.id.container, childFragmentManager)
        navigatorHolder.setNavigator(navigator)

        viewModel.openProfileScreen()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}