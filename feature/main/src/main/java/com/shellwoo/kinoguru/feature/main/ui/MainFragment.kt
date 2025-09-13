package com.shellwoo.kinoguru.feature.main.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.navigation.NavigatorFactory
import com.shellwoo.kinoguru.core.presentation.observeAsNotNullableState
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.component.NO_CONTENT_LAYOUT_ID
import com.shellwoo.kinoguru.core.ui.ext.createComposeView
import com.shellwoo.kinoguru.feature.main.R
import com.shellwoo.kinoguru.feature.main.di.MainComponentViewModel
import com.shellwoo.kinoguru.feature.main.navigation.MainNavigatorHolder
import com.shellwoo.kinoguru.feature.main.presentation.MainViewModel
import javax.inject.Inject

class MainFragment : BaseFragment(NO_CONTENT_LAYOUT_ID) {

    @Inject
    lateinit var navigatorHolder: MainNavigatorHolder

    @Inject
    lateinit var navigatorFactory: NavigatorFactory

    private val componentViewModel: MainComponentViewModel by viewModels()

    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val navigator by lazy { navigatorFactory.create(requireActivity(), R.id.container, childFragmentManager) }

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
        parentFragmentManager.commit { setPrimaryNavigationFragment(this@MainFragment) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        createComposeView {
            val state by viewModel.state.observeAsNotNullableState()

            MainScreen(state, viewModel::selectTab)
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