package com.shellwoo.kinoguru.feature.movie.search.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.core.ui.component.AfterTextWatcher
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.hideKeyboard
import com.shellwoo.kinoguru.feature.movie.search.R
import com.shellwoo.kinoguru.feature.movie.search.databinding.MovieSearchFragmentBinding
import com.shellwoo.kinoguru.feature.movie.search.di.MovieSearchComponentViewModel
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchViewModel
import com.shellwoo.kinoguru.feature.movie.search.presentation.ScreenState
import com.shellwoo.kinoguru.feature.movie.search.presentation.SearchState
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverter
import com.shellwoo.kinoguru.shared.error.ui.showErrorDialog
import com.shellwoo.kinoguru.shared.movie.ui.RatingFormatter
import com.shellwoo.kinoguru.shared.onboarding.ui.OnboardingDialogFragment
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import javax.inject.Inject

class MovieSearchFragment : BaseFragment(R.layout.movie_search_fragment) {

    @Inject
    lateinit var ratingFormatter: RatingFormatter

    @Inject
    lateinit var diffUtilCallback: MovieSearchItemDiffUtilCallback

    @Inject
    lateinit var baseExceptionMessageConverter: BaseExceptionMessageConverter

    private val componentViewModel: MovieSearchComponentViewModel by viewModels()
    private val viewModel: MovieSearchViewModel by viewModels(factoryProducer = ::viewModelFactory)
    private val binding by viewBinding(MovieSearchFragmentBinding::bind)

    private val moviesOnScrollListener = object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == SCROLL_STATE_DRAGGING) hideKeyboard()
        }
    }

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
        binding.movies.adapter = MovieSearchItemAdapter(ratingFormatter, viewModel::selectMovieSuccessItem, diffUtilCallback)
        binding.movies.itemAnimator = ScaleInAnimator()
    }

    private fun initListeners() {
        binding.searchInput.addTextChangedListener(AfterTextWatcher(viewModel::setQuery))
        binding.movies.addOnScrollListener(moviesOnScrollListener)
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
        binding.toolbar.isVisible = false
        binding.searchInput.isVisible = false
    }

    private fun renderContentState(state: ScreenState.Content) {
        binding.toolbar.isVisible = true
        binding.searchInput.isVisible = true
        renderSearchState(state.searchState)
    }

    private fun renderSearchState(state: SearchState) {
        when (state) {
            SearchState.None -> renderNoneSearchState()
            is SearchState.Items -> renderSuccessSearchState(state)
            SearchState.NotFound -> renderNotFoundSearchState()
        }
    }

    private fun renderNoneSearchState() {
        binding.movies.isVisible = false
        binding.notFound.isVisible = false
    }

    private fun renderSuccessSearchState(state: SearchState.Items) {
        (binding.movies.adapter as MovieSearchItemAdapter).submitList(state.value)
        binding.movies.isVisible = true
        binding.notFound.isVisible = false
    }

    private fun renderNotFoundSearchState() {
        binding.movies.isVisible = false
        binding.notFound.isVisible = true
    }

    private fun renderOnboarding() {
        OnboardingDialogFragment.show(
            fragmentManager = childFragmentManager,
            targetView = binding.searchInputLayout,
            description = getString(R.string.movie_search_input_onboarding)
        )
    }

    private fun renderSearchError(baseException: BaseException) {
        showErrorDialog(
            baseExceptionMessageConverter = baseExceptionMessageConverter,
            baseException = baseException,
            retryAction = viewModel::search,
        )
    }
}