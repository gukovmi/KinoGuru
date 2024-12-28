package com.shellwoo.kinoguru.feature.movie.detail.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.presentation.observeAsNotNullableState
import com.shellwoo.kinoguru.core.ui.component.NO_CONTENT_LAYOUT_ID
import com.shellwoo.kinoguru.core.ui.ext.createComposeView
import com.shellwoo.kinoguru.feature.movie.detail.di.MovieDetailsComponentViewModel
import com.shellwoo.kinoguru.feature.movie.detail.di.MovieDetailsViewModelFactory
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsViewModel
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverter
import com.shellwoo.kinoguru.shared.error.ui.showErrorDialog
import com.shellwoo.kinoguru.shared.movie.ui.RatingFormatter
import javax.inject.Inject

class MovieDetailsFragment : Fragment(NO_CONTENT_LAYOUT_ID) {

    companion object {

        fun newInstance(movieId: Int): MovieDetailsFragment =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply { putInt(MOVIE_ID_ARG_NAME, movieId) }
            }

        private const val MOVIE_ID_ARG_NAME = "MOVIE_ID_ARG"
    }

    private val componentViewModel: MovieDetailsComponentViewModel by viewModels()

    @Inject
    lateinit var movieDetailsViewModelFactory: MovieDetailsViewModelFactory.Factory
    private val viewModel: MovieDetailsViewModel by viewModels(
        factoryProducer = { movieDetailsViewModelFactory.create(requireArguments().getInt(MOVIE_ID_ARG_NAME)) }
    )

    @Inject
    lateinit var rattingFormatter: RatingFormatter

    @Inject
    lateinit var baseExceptionMessageConverter: BaseExceptionMessageConverter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        createComposeView {
            val state by viewModel.state.observeAsNotNullableState()

            MovieDetailsScreen(
                state = state,
                onNavigationIconClick = viewModel::close,
                rattingFormatter = rattingFormatter::format,
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        viewModel.start()
    }

    private fun observeViewModel() {
        viewModel.loadMovieDetailsErrorEvent.observe(viewLifecycleOwner, ::renderMovieDetailsLoadingError)
    }

    private fun renderMovieDetailsLoadingError(baseException: BaseException) {
        showErrorDialog(
            baseExceptionMessageConverter = baseExceptionMessageConverter,
            baseException = baseException,
            retryAction = viewModel::loadMovieDetails,
            cancelAction = viewModel::close,
            okAction = viewModel::close,
        )
    }
}