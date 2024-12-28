package com.shellwoo.kinoguru.feature.movie.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.view.isVisible
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.shellwoo.kinoguru.design.resource.AppTheme
import com.shellwoo.kinoguru.design.uikit.AppBar
import com.shellwoo.kinoguru.design.uikit.NavigationIcon
import com.shellwoo.kinoguru.design.uikit.NavigationIconParams
import com.shellwoo.kinoguru.feature.movie.detail.R
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsState
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieVideoItem
import com.shellwoo.kinoguru.shared.movie.BaseUrls.THE_MOVIE_DB_IMAGE
import com.valentinilk.shimmer.shimmer

@Composable
internal fun MovieDetailsScreen(
    state: MovieDetailsState,
    onNavigationIconClick: () -> Unit,
    rattingFormatter: (Double?) -> String,
) {
    when (state) {
        MovieDetailsState.Initial -> Unit
        MovieDetailsState.Loading -> LoadingState(onNavigationIconClick)
        is MovieDetailsState.Content -> ContentState(
            state,
            onNavigationIconClick,
            rattingFormatter,
        )
    }
}

@Composable
private fun LoadingState(
    onNavigationIconClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MovieDetailsAppBar(onNavigationIconClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .shimmer()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
            ) {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(22.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ContentState(
    state: MovieDetailsState.Content,
    onNavigationIconClick: () -> Unit,
    rattingFormatter: (Double?) -> String,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MovieDetailsAppBar(onNavigationIconClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Poster(posterPath = state.movieDetails.posterPath, backdropPath = state.movieDetails.backdropPath)

            val title = state.movieDetails.title
            if (title != null) {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            val overview = state.movieDetails.overview
            if (overview != null) {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    text = overview,
                    textAlign = TextAlign.Center,
                )
            }

            FlowRow(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val ratting = state.movieDetails.voteAverage
                if (ratting != null) {
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = AnnotatedString.fromHtml(
                            stringResource(R.string.movie_details_rating, rattingFormatter(ratting))
                        )
                    )
                }

                val releaseDate = state.movieDetails.releaseDate
                if (releaseDate != null) {
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = AnnotatedString.fromHtml(
                            stringResource(
                                R.string.movie_details_release_date,
                                releaseDate,
                            )
                        ),
                    )
                }

                val budget = state.movieDetails.budget
                if (budget != null) {
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = AnnotatedString.fromHtml(
                            stringResource(R.string.movie_details_budget, budget)
                        )
                    )
                }

                val revenue = state.movieDetails.revenue
                if (revenue != null) {
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = AnnotatedString.fromHtml(
                            stringResource(R.string.movie_details_revenue, revenue)
                        )
                    )
                }

                val genres = state.movieDetails.genres
                if (genres.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = AnnotatedString.fromHtml(
                            stringResource(
                                R.string.movie_details_genres,
                                genres.mapNotNull { it.name }.formatWithSeparator(),
                            )
                        ),
                        textAlign = TextAlign.Center,
                    )
                }

                val productionCompanies = state.movieDetails.productionCompanies
                if (productionCompanies.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = AnnotatedString.fromHtml(
                            stringResource(
                                R.string.movie_details_companies,
                                state.movieDetails.productionCompanies.mapNotNull { it.name }.formatWithSeparator(),
                            )
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }

            val movieVideoItems = state.movieVideoItems
            if (movieVideoItems.isNotEmpty()) {
                Videos(items = movieVideoItems)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Poster(
    posterPath: String?,
    backdropPath: String?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        contentAlignment = Alignment.Center,
    ) {
        GlideSubcomposition(
            modifier = Modifier
                .fillMaxSize(),
            model = THE_MOVIE_DB_IMAGE + backdropPath,
            requestBuilderTransform = { it.thumbnail() }) {
            when (state) {
                RequestState.Failure -> Unit

                RequestState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                            .shimmer()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        )
                    }
                }

                is RequestState.Success -> Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(6.dp),
                    contentDescription = null,
                    painter = painter,
                )
            }
        }

        GlideSubcomposition(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .aspectRatio(2 / 3f),
            model = THE_MOVIE_DB_IMAGE + posterPath,
            requestBuilderTransform = {
                it.thumbnail()
            }) {
            when (state) {
                RequestState.Failure -> Unit

                RequestState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmer()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        )
                    }
                }

                is RequestState.Success -> Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.dp, MaterialTheme.colorScheme.onBackground),
                    painter = painter,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun Videos(items: List<MovieVideoItem>) {
    val pagerState = rememberPagerState { items.size }
    val item = remember(items) { items[pagerState.currentPage] }

    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        text = AnnotatedString.fromHtml(
            stringResource(
                R.string.movie_details_video_description,
                pagerState.currentPage + 1, items.size, item.name,
            )
        ),
        textAlign = TextAlign.Center,
    )

    HorizontalPager(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        state = pagerState
    ) { _ ->
        if (item is MovieVideoItem.YouTube) {
            YoutubeVideo(item)
        }
    }
}

@Composable
private fun YoutubeVideo(item: MovieVideoItem.YouTube) {
    var playerState by remember { mutableStateOf(YoutubePlayerState.LOADING) }

    when (playerState) {
        YoutubePlayerState.LOADING -> {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxSize()
                    .shimmer()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                )
            }
        }

        YoutubePlayerState.ERROR -> {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.movie_details_video_error),
                textAlign = TextAlign.Center,
            )
        }

        YoutubePlayerState.CONTENT -> Unit
    }

    AndroidView(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        factory = {
            YouTubePlayerView(it).apply {
                addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            playerState = YoutubePlayerState.LOADING
                            isVisible = false
                            youTubePlayer.cueVideo(item.id, 0f)
                        }

                        override fun onStateChange(
                            youTubePlayer: YouTubePlayer,
                            state: PlayerConstants.PlayerState
                        ) {
                            if (state == PlayerConstants.PlayerState.VIDEO_CUED) {
                                playerState = YoutubePlayerState.CONTENT
                                isVisible = true
                            }
                        }

                        override fun onError(
                            youTubePlayer: YouTubePlayer,
                            error: PlayerConstants.PlayerError
                        ) {
                            playerState = YoutubePlayerState.ERROR
                            isVisible = false
                        }
                    }
                )
            }
        }
    )
}

private enum class YoutubePlayerState {
    LOADING,
    CONTENT,
    ERROR,
}

@Composable
private fun MovieDetailsAppBar(onNavigationIconClick: () -> Unit) {
    AppBar(
        text = stringResource(R.string.movie_details_title),
        navigationIconParams = NavigationIconParams(
            NavigationIcon.BACK,
            onNavigationIconClick,
        )
    )
}

private fun List<*>.formatWithSeparator(): String =
    joinToString(separator = ", ")

@PreviewLightDark
@Composable
private fun MovieDetailsPreview() {
    val movieDetails = MovieDetails(
        backdropPath = "backdropPath",
        budget = 100000,
        genres = ArrayList(),
        id = 123,
        overview = "После двух лет поисков правосудия на улицах Готэма для своих сограждан Бэтмен становится олицетворением беспощадного возмездия. Когда в городе происходит серия жестоких нападений на представителей элиты, загадочные улики приводят Брюса Уэйна в самые темные закоулки преступного мира, где он встречает Женщину-Кошку, Пингвина, Кармайна Фальконе и Загадочника. Теперь под прицелом оказывается сам Бэтмен, которому предстоит отличить друга от врага и восстановить справедливость во имя Готэма.",
        posterPath = null,
        productionCompanies = ArrayList(),
        releaseDate = "11.11.2011",
        revenue = 200000,
        title = "Batman",
        voteAverage = 9.55,
    )
    val movieVideoItems: List<MovieVideoItem> = emptyList()
    val state = MovieDetailsState.Content(movieDetails, movieVideoItems)

    AppTheme {
        Surface {
            MovieDetailsScreen(
                state = state,
                onNavigationIconClick = {},
                rattingFormatter = { it.toString() },
            )
        }
    }
}