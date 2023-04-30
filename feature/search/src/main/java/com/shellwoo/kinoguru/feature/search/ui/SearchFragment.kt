package com.shellwoo.kinoguru.feature.search.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.ui.component.AfterTextWatcher
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.feature.search.R
import com.shellwoo.kinoguru.feature.search.di.SearchComponentViewModel
import com.shellwoo.kinoguru.feature.search.presentation.ScreenState
import com.shellwoo.kinoguru.feature.search.presentation.SearchState
import com.shellwoo.kinoguru.feature.search.presentation.SearchViewModel
import com.shellwoo.kinoguru.shared.onboarding.ui.OnboardingDialogFragment
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : BaseFragment(R.layout.search_fragment) {

    @Inject
    lateinit var adapter: SearchMovieItemAdapter

    private val componentViewModel: SearchComponentViewModel by viewModels()
    private val viewModel: SearchViewModel by viewModels(factoryProducer = ::viewModelFactory)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies.adapter = adapter
        initListeners()
        observeViewModel()

        if (savedInstanceState == null) {
            viewModel.start()
        }
    }

    private fun initListeners() {
        searchInput.addTextChangedListener(AfterTextWatcher(viewModel::search))
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.onboardingEvent.observe(viewLifecycleOwner) { showOnboarding() }
    }

    private fun renderState(state: ScreenState) {
        when (state) {
            ScreenState.Initial -> renderInitialState()
            is ScreenState.Content -> renderContentState(state)
        }
    }

    private fun renderInitialState() {
        toolbar.isVisible = false
        searchInput.isVisible = false
    }

    private fun renderContentState(state: ScreenState.Content) {
        toolbar.isVisible = true
        searchInput.isVisible = true
        renderSearchState(state.searchState)
    }

    private fun renderSearchState(state: SearchState) {
        when (state) {
            SearchState.None -> renderNoneSearchState()
            is SearchState.Result -> renderSuccessfulSearchState(state)
        }
    }

    private fun renderNoneSearchState() {
        movies.isVisible = false
    }

    private fun renderSuccessfulSearchState(state: SearchState.Result) {
        (movies.adapter as SearchMovieItemAdapter).submitList(state.items)
        movies.isVisible = true
    }

    private fun showOnboarding() {
        OnboardingDialogFragment.show(
            fragmentManager = childFragmentManager,
            targetView = searchInputLayout,
            description = getString(R.string.search_input_onboarding)
        )
    }
}