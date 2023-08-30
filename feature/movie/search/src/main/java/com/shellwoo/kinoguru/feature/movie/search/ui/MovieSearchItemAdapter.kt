package com.shellwoo.kinoguru.feature.movie.search.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchItem
import com.shellwoo.kinoguru.shared.movie.ui.RatingFormatter
import javax.inject.Inject

class MovieSearchItemAdapter @Inject constructor(
    private val ratingFormatter: RatingFormatter,
    private val onSearchMovieSuccessItemClickListener: (MovieSearchItem.Success) -> Unit,
    diffUtilCallback: MovieSearchItemDiffUtilCallback,
) :
    ListAdapter<MovieSearchItem, RecyclerView.ViewHolder>(diffUtilCallback) {

    private companion object {

        const val LOADING_VIEW_TYPE = 0
        const val SUCCESS_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LOADING_VIEW_TYPE -> MovieSearchLoadingItemViewHolder(parent)
            SUCCESS_VIEW_TYPE -> MovieSearchSuccessItemViewHolder(ratingFormatter, parent)
            else -> error("ViewType: $viewType is invalid")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieSearchSuccessItemViewHolder -> holder.bind(
                getItem(position) as MovieSearchItem.Success,
                onSearchMovieSuccessItemClickListener,
            )
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            MovieSearchItem.Loading -> LOADING_VIEW_TYPE
            is MovieSearchItem.Success -> SUCCESS_VIEW_TYPE
        }
}