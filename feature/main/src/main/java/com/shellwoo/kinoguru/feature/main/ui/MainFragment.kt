package com.shellwoo.kinoguru.feature.main.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import com.github.terrakok.cicerone.NavigatorHolder
import com.shellwoo.kinoguru.core.navigation.NavigatorFactory
import com.shellwoo.kinoguru.core.navigation.di.MainNavigation
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.feature.main.R
import com.shellwoo.kinoguru.feature.main.presentation.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : BaseFragment(R.layout.main_fragment) {

    @MainNavigation
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var navigatorFactory: NavigatorFactory

    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val navigator by lazy { navigatorFactory.create(requireActivity(), R.id.container, childFragmentManager) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        bottomNavigationBar.selectedItemId = R.id.profile
    }

    private fun initListeners() {
        bottomNavigationBar.setOnItemSelectedListener(::handleSelectedMenuItem)
    }

    private fun handleSelectedMenuItem(menuItem: MenuItem): Boolean {
        val selectedItemNotChanged = bottomNavigationBar.selectedItemId == menuItem.itemId
        if (selectedItemNotChanged) return false

        when (menuItem.itemId) {
            R.id.profile -> viewModel.openProfileScreen()
        }
        return true
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