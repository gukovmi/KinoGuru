package com.shellwoo.kinoguru.feature.main.ui

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.navigation.NavigatorFactory
import com.shellwoo.kinoguru.core.ui.component.BaseDaggerFragment
import com.shellwoo.kinoguru.feature.main.R
import com.shellwoo.kinoguru.feature.main.navigation.MainNavigatorHolder
import com.shellwoo.kinoguru.feature.main.presentation.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : BaseDaggerFragment(R.layout.main_fragment) {

    @Inject
    lateinit var navigatorHolder: MainNavigatorHolder

    @Inject
    lateinit var navigatorFactory: NavigatorFactory

    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val navigator by lazy { navigatorFactory.create(requireActivity(), R.id.container, childFragmentManager) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        if (savedInstanceState == null) {
            bottomNavigationBar.selectedItemId = R.id.search
        }
    }

    private fun initListeners() {
        bottomNavigationBar.setOnItemSelectedListener(::handleSelectedMenuItem)
    }

    private fun handleSelectedMenuItem(menuItem: MenuItem): Boolean {
        val selectedItemNotChanged = bottomNavigationBar.selectedItemId == menuItem.itemId
        if (selectedItemNotChanged) return false

        when (menuItem.itemId) {
            R.id.profile -> viewModel.openProfileScreen()
            R.id.search -> viewModel.openSearchScreen()
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.holder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.holder.removeNavigator()
        super.onPause()
    }
}