package com.shellwoo.kinoguru.feature.search.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.ui.component.BaseDaggerFragment
import com.shellwoo.kinoguru.feature.search.R
import com.shellwoo.kinoguru.feature.search.presentation.SearchState
import com.shellwoo.kinoguru.feature.search.presentation.SearchViewModel
import com.shellwoo.kinoguru.shared.onboarding.ui.OnboardingDialogFragment
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : BaseDaggerFragment(R.layout.search_fragment) {

    private val viewModel: SearchViewModel by viewModels(factoryProducer = ::viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        if (savedInstanceState == null) {
            viewModel.start()
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.onboardingEvent.observe(viewLifecycleOwner) { showOnboarding() }
    }

    private fun renderState(state: SearchState) {
        when (state) {
            SearchState.Initial -> renderInitialState()
            SearchState.Content -> renderContentState()
        }
    }

    private fun renderInitialState() {
        toolbar.isVisible = false
        searchInput.isVisible = false
    }

    private fun renderContentState() {
        toolbar.isVisible = true
        searchInput.isVisible = true
    }

    private fun showOnboarding() {
        OnboardingDialogFragment.show(
            fragmentManager = childFragmentManager,
            targetView = searchInputLayout,
            description = getString(R.string.search_input_onboarding)
        )
    }
}