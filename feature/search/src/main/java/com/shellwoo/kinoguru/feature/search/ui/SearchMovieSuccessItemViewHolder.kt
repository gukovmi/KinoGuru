package com.shellwoo.kinoguru.feature.search.ui

import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.shellwoo.kinoguru.core.ui.ext.getThemeColor
import com.shellwoo.kinoguru.core.ui.ext.inflate
import com.shellwoo.kinoguru.feature.search.BaseUrls
import com.shellwoo.kinoguru.feature.search.R
import com.shellwoo.kinoguru.feature.search.presentation.SearchMovieItem
import kotlinx.android.synthetic.main.search_movie_success_item.view.*
import com.shellwoo.kinoguru.design.resource.R as designR

class SearchMovieSuccessItemViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.search_movie_success_item, false)) {

    private companion object {

        const val NOT_FOUND_DATA = "-"
        const val RATING_FORMAT_PATTERN = "%.2f"
    }

    private val requestManager: RequestManager by lazy { Glide.with(itemView) }

    fun bind(item: SearchMovieItem.Success) {
        val movie = item.value
        renderPoster(movie.posterPath)
        renderRating(movie.voteAverage)
        with(itemView) {
            title.text = movie.title
            description.text = movie.overview
        }
    }

    private fun renderPoster(posterPath: String?) {
        val posterUrl = posterPath?.let(BaseUrls.THE_MOVIE_DB_IMAGE::plus)

        if (posterPath != null) {
            requestManager.load(posterUrl)
                .placeholder(android.R.drawable.ic_menu_camera)
                .error(android.R.drawable.ic_menu_camera)
                .into(itemView.poster)
        }
    }

    private fun renderRating(value: Double?) {
        with(itemView) {
            rating.setBackgroundColor(getRatingBackgroundColor(value))
            rating.text = getRatingText(value)
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

    private fun getRatingText(value: Double?): String =
        value?.let { RATING_FORMAT_PATTERN.format(it) } ?: NOT_FOUND_DATA
}