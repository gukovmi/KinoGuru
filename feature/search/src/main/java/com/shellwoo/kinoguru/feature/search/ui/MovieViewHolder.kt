package com.shellwoo.kinoguru.feature.search.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.shellwoo.kinoguru.feature.search.BaseUrls
import com.shellwoo.kinoguru.feature.search.R
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovie
import kotlinx.android.synthetic.main.movie_search_result_item.view.*

class MovieViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_search_result_item, parent, false)) {

    private companion object {

        const val NOT_FOUND_DATA = "-"
        const val RATING_FORMAT_PATTERN = "%.2f"
    }

    private val requestManager: RequestManager by lazy { Glide.with(itemView) }

    fun bind(movie: SearchMovie) {
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
            null -> Color.GRAY
            in 0.1..5.0 -> Color.RED
            in 7.0..10.0 -> Color.GREEN
            else -> Color.GRAY
        }

    private fun getRatingText(value: Double?): String =
        value?.let { RATING_FORMAT_PATTERN.format(it) } ?: NOT_FOUND_DATA
}