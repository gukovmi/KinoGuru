package com.shellwoo.kinoguru.feature.movie.search.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.ui.component.AfterTextWatcher
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.feature.movie.search.R
import com.shellwoo.kinoguru.feature.movie.search.di.MovieSearchComponentViewModel
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchViewModel
import com.shellwoo.kinoguru.feature.movie.search.presentation.ScreenState
import com.shellwoo.kinoguru.feature.movie.search.presentation.SearchState
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.ui.showErrorDialog
import com.shellwoo.kinoguru.shared.movie.ui.RatingFormatter
import com.shellwoo.kinoguru.shared.onboarding.ui.OnboardingDialogFragment
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.movie_search_fragment.*
import javax.inject.Inject

class MovieSearchFragment : BaseFragment(R.layout.movie_search_fragment) {

    @Inject
    lateinit var ratingFormatter: RatingFormatter

    @Inject
    lateinit var diffUtilCallback: MovieSearchItemDiffUtilCallback

    private val componentViewModel: MovieSearchComponentViewModel by viewModels()
    private val viewModel: MovieSearchViewModel by viewModels(factoryProducer = ::viewModelFactory)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initListeners()
        observeViewModel()

        if (savedInstanceState == null) {
            viewModel.start()
        }
    }

    private fun initRecycler() {
        movies.adapter = MovieSearchItemAdapter(ratingFormatter, viewModel::selectMovieSuccessItem, diffUtilCallback)
        movies.itemAnimator = ScaleInAnimator()
    }

    private fun initListeners() {
        searchInput.addTextChangedListener(AfterTextWatcher(viewModel::setQuery))
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.onboardingEvent.observe(viewLifecycleOwner) { renderOnboarding() }
        viewModel.searchErrorEvent.observe(viewLifecycleOwner, ::renderSearchError)
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
        (movies.adapter as MovieSearchItemAdapter).submitList(state.items)
        movies.isVisible = true
    }

    private fun renderOnboarding() {
        OnboardingDialogFragment.show(
            fragmentManager = childFragmentManager,
            targetView = searchInputLayout,
            description = getString(R.string.movie_search_input_onboarding)
        )
    }

    private fun renderSearchError(baseException: BaseException) {
        showErrorDialog(
            baseException = baseException,
            retryAction = viewModel::search,
        )
    }
}