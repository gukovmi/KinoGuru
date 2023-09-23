package com.shellwoo.kinoguru.feature.movie.detail.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shellwoo.kinoguru.core.ktx.fromHtml
import com.shellwoo.kinoguru.feature.movie.detail.R
import com.shellwoo.kinoguru.feature.movie.detail.data.model.GenreModel
import com.shellwoo.kinoguru.feature.movie.detail.data.model.ProductionCompanyModel
import com.shellwoo.kinoguru.feature.movie.detail.di.MovieDetailsComponentViewModel
import com.shellwoo.kinoguru.feature.movie.detail.di.MovieDetailsViewModelFactory
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsState
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsViewModel
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieVideoItem
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverter
import com.shellwoo.kinoguru.shared.error.ui.showErrorDialog
import com.shellwoo.kinoguru.shared.movie.BaseUrls
import com.shellwoo.kinoguru.shared.movie.ui.RatingFormatter
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.movie_details_content.*
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlinx.android.synthetic.main.movie_details_loading.*
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    companion object {

        fun newInstance(movieId: Int): MovieDetailsFragment =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply { putInt(MOVIE_ID_ARG_NAME, movieId) }
            }

        private const val MOVIE_ID_ARG_NAME = "MOVIE_ID_ARG"
        private const val NOT_FOUND_DATA = "-"
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

    @Inject
    lateinit var movieVideoItemAdapter: MovieVideoItemAdapter

    private val requestManager: RequestManager by lazy { Glide.with(this) }
    private val backgroundRequestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean =
            onBackgroundImageFinishLoading()

        override fun onResourceReady(
            resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
        ): Boolean =
            onBackgroundImageFinishLoading()
    }
    private val posterRequestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean =
            onPosterImageFinishLoading()

        override fun onResourceReady(
            resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
        ): Boolean =
            onPosterImageFinishLoading()
    }

    private fun onBackgroundImageFinishLoading(): Boolean {
        renderBackgroundLoaded()
        return false
    }

    private fun onPosterImageFinishLoading(): Boolean {
        renderPosterLoaded()
        return false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { viewModel.close() }
        videos.adapter = movieVideoItemAdapter
        observeViewModel()

        viewModel.start()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.loadMovieDetailsErrorEvent.observe(viewLifecycleOwner, ::renderMovieDetailsLoadingError)
    }

    private fun renderState(state: MovieDetailsState) {
        when (state) {
            MovieDetailsState.Initial -> renderInitialState()
            MovieDetailsState.Loading -> renderLoadingState()
            is MovieDetailsState.Content -> renderContentState(state)
        }
    }

    private fun renderInitialState() {
        loading.isVisible = false
        content.isVisible = false
    }

    private fun renderLoadingState() {
        loading.isVisible = true
        content.isVisible = false
    }

    private fun renderContentState(contentState: MovieDetailsState.Content) {
        loading.isVisible = false
        content.isVisible = true

        with(contentState.movieDetails) {
            renderBackground(backdropPath)
            renderPoster(posterPath)
            renderTitle(title)
            renderRating(voteAverage)
            renderReleaseDate(releaseDate)
            renderBudget(budget)
            renderRevenue(revenue)
            renderGenres(genres)
            renderDescription(overview)
            renderCompanies(productionCompanies)
        }
        renderVideos(contentState.movieVideoItems)
    }

    private fun renderBackground(backdropPath: String?) {
        val backgroundUrl = backdropPath?.let(BaseUrls.THE_MOVIE_DB_IMAGE::plus)
        if (backgroundUrl != null) {
            renderBackgroundLoading()

            requestManager.load(backgroundUrl)
                .transform(BlurTransformation())
                .listener(backgroundRequestListener)
                .into(background)
        } else {
            renderBackgroundLoaded()
        }
    }

    private fun renderPoster(posterPath: String?) {
        val posterUrl = posterPath?.let(BaseUrls.THE_MOVIE_DB_IMAGE::plus)
        if (posterUrl != null) {
            renderPosterLoading()

            requestManager.load(posterUrl)
                .listener(posterRequestListener)
                .error(android.R.drawable.ic_menu_camera)
                .into(poster)
        } else {
            renderPosterLoaded()
            poster.setImageResource(android.R.drawable.ic_menu_camera)
        }
    }

    private fun renderRating(voteAverage: Double?) {
        val formattedRating = rattingFormatter.format(voteAverage)
        rating.text = getString(R.string.movie_details_rating, formattedRating).fromHtml()
    }

    private fun renderReleaseDate(releaseDateValue: String?) {
        val formattedReleaseDate = releaseDateValue ?: NOT_FOUND_DATA
        releaseDate.text = getString(R.string.movie_details_release_date, formattedReleaseDate).fromHtml()
    }

    private fun renderTitle(titleValue: String?) {
        val formattedTitle = titleValue ?: NOT_FOUND_DATA
        title.text = formattedTitle
    }

    private fun renderBudget(budgetValue: Int?) {
        val formattedBudget = budgetValue ?: NOT_FOUND_DATA
        budget.text = getString(R.string.movie_details_budget, formattedBudget).fromHtml()
    }

    private fun renderRevenue(revenueValue: Int?) {
        val formattedRevenue = revenueValue ?: NOT_FOUND_DATA
        revenue.text = getString(R.string.movie_details_revenue, formattedRevenue).fromHtml()
    }

    private fun renderGenres(genresValue: ArrayList<GenreModel>) {
        val formattedGenres = genresValue.mapNotNull { it.name }.formatWithSeparator()
        genres.text = getString(R.string.movie_details_genres, formattedGenres).fromHtml()
    }

    private fun renderDescription(overview: String?) {
        val formattedDescription = overview ?: NOT_FOUND_DATA
        description.text = getString(R.string.movie_details_description, formattedDescription).fromHtml()
    }

    private fun renderCompanies(productionCompanies: ArrayList<ProductionCompanyModel>) {
        val formattedCompanies = productionCompanies.mapNotNull { it.name }.formatWithSeparator()
        companies.text = getString(R.string.movie_details_companies, formattedCompanies).fromHtml()
    }

    private fun List<*>.formatWithSeparator(): String =
        joinToString(separator = ", ").ifEmpty { NOT_FOUND_DATA }

    private fun renderBackgroundLoading() {
        background.isInvisible = true
        backgroundSkeleton.isVisible = true
    }

    private fun renderBackgroundLoaded() {
        background.isInvisible = false
        backgroundSkeleton.isVisible = false
    }

    private fun renderPosterLoading() {
        poster.isInvisible = true
        posterSkeleton.isVisible = true
    }

    private fun renderPosterLoaded() {
        poster.isInvisible = false
        posterSkeleton.isVisible = false
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

    private fun renderVideos(movieVideoItems: List<MovieVideoItem>) {
        if (movieVideoItems.isEmpty()) {
            videos.isVisible = false
        } else {
            videos.isVisible = true
            movieVideoItemAdapter.submitList(movieVideoItems)
        }
    }

    override fun onDestroyView() {
        videos.adapter = null
        movieVideoItemAdapter.submitList(emptyList())
        super.onDestroyView()
    }
}