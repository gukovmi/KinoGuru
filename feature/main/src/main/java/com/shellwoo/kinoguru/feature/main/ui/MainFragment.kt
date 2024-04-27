package com.shellwoo.kinoguru.feature.main.ui

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.core.navigation.NavigatorFactory
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.feature.main.R
import com.shellwoo.kinoguru.feature.main.databinding.MainFragmentBinding
import com.shellwoo.kinoguru.feature.main.di.MainComponentViewModel
import com.shellwoo.kinoguru.feature.main.navigation.MainNavigatorHolder
import com.shellwoo.kinoguru.feature.main.presentation.MainViewModel
import javax.inject.Inject

class MainFragment : BaseFragment(R.layout.main_fragment) {

    @Inject
    lateinit var navigatorHolder: MainNavigatorHolder

    @Inject
    lateinit var navigatorFactory: NavigatorFactory

    private val componentViewModel: MainComponentViewModel by viewModels()

    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val binding by viewBinding(MainFragmentBinding::bind)

    private val navigator by lazy { navigatorFactory.create(requireActivity(), R.id.container, childFragmentManager) }

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
        parentFragmentManager.commit { setPrimaryNavigationFragment(this@MainFragment) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        if (savedInstanceState == null) {
            binding.bottomNavigationBar.selectedItemId = R.id.search
        }
    }

    private fun initListeners() {
        binding.bottomNavigationBar.setOnItemSelectedListener(::handleSelectedMenuItem)
    }

    private fun handleSelectedMenuItem(menuItem: MenuItem): Boolean {
        val selectedItemNotChanged = binding.bottomNavigationBar.selectedItemId == menuItem.itemId
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