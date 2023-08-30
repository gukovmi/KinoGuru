package com.shellwoo.kinoguru.feature.movie.search.ui

import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.shellwoo.kinoguru.core.ui.ext.getThemeColor
import com.shellwoo.kinoguru.core.ui.ext.inflate
import com.shellwoo.kinoguru.feature.movie.search.R
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchItem
import com.shellwoo.kinoguru.shared.movie.BaseUrls
import com.shellwoo.kinoguru.shared.movie.ui.RatingFormatter
import kotlinx.android.synthetic.main.movie_search_success_item.view.*
import com.shellwoo.kinoguru.design.resource.R as designR

class MovieSearchSuccessItemViewHolder(
    private val ratingFormatter: RatingFormatter,
    parent: ViewGroup,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.movie_search_success_item, false)) {

    private val requestManager: RequestManager by lazy { Glide.with(itemView) }

    fun bind(item: MovieSearchItem.Success, onClickListener: (MovieSearchItem.Success) -> Unit) {
        val movie = item.value
        renderPoster(movie.posterPath)
        renderRating(movie.voteAverage)
        with(itemView) {
            title.text = movie.title
            description.text = movie.overview
            setOnClickListener { onClickListener(item) }
        }
    }

    private fun renderPoster(posterPath: String?) {
        val posterUrl = posterPath?.let(BaseUrls.THE_MOVIE_DB_IMAGE::plus)

        if (posterUrl != null) {
            requestManager.load(posterUrl)
                .placeholder(android.R.drawable.ic_menu_camera)
                .error(android.R.drawable.ic_menu_camera)
                .into(itemView.poster)
        }
    }

    private fun renderRating(value: Double?) {
        with(itemView) {
            rating.setBackgroundColor(getRatingBackgroundColor(value))
            rating.text = ratingFormatter.format(value)
        }
    }

    @ColorInt
    private fun getRatingBackgroundColor(value: Double?): Int =
        when (value) {
            null -> getThemeColor(itemView.context, designR.attr.colorPermanentNeutral)
            in 0.1..5.0 -> getThemeColor(itemView.context, designR.attr.colorPermanentNegative)
            in 7.0..10.0 -> getThemeColor(itemView.context, designR.attr.colorPermanentPositive)
            else -> getThemeColor(itemView.context, designR.attr.colorPermanentNeutral)
        }
}