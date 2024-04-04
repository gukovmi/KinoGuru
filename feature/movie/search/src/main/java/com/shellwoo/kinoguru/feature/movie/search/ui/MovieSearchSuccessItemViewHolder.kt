package com.shellwoo.kinoguru.feature.movie.search.ui

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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

    private val posterRequestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean): Boolean =
            onPosterLoaded()

        override fun onResourceReady(
            resource: Drawable, model: Any, target: Target<Drawable>?, dataSource: DataSource, isFirstResource: Boolean,
        ): Boolean =
            onPosterLoaded()
    }

    private fun onPosterLoaded(): Boolean {
        renderPosterLoaded()
        return false
    }

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
            renderPosterLoading()

            requestManager.load(posterUrl)
                .placeholder(android.R.drawable.ic_menu_camera)
                .listener(posterRequestListener)
                .error(android.R.drawable.ic_menu_camera)
                .into(itemView.poster)
        } else {
            renderPosterLoaded()

            itemView.poster.setImageResource(android.R.drawable.ic_menu_camera)
        }
    }

    private fun renderPosterLoading() {
        itemView.poster.isInvisible = true
        itemView.posterSkeleton.isVisible = true
    }

    private fun renderPosterLoaded() {
        itemView.poster.isInvisible = false
        itemView.posterSkeleton.isVisible = false
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