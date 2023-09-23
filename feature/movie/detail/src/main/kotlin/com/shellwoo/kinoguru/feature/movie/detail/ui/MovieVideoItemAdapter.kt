package com.shellwoo.kinoguru.feature.movie.detail.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieVideoItem
import javax.inject.Inject

class MovieVideoItemAdapter @Inject constructor(
    diffUtilCallback: MovieVideoItemDiffUtilCallback,
) : ListAdapter<MovieVideoItem, RecyclerView.ViewHolder>(diffUtilCallback) {

    private companion object {

        const val MOVIE_VIDEO_ITEM_YOU_TUBE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            MOVIE_VIDEO_ITEM_YOU_TUBE -> MovieVideoItemYouTubeViewHolder(parent)
            else -> error("ViewType: $viewType is invalid")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieVideoItemYouTubeViewHolder -> holder.bind(getItem(position) as MovieVideoItem.YouTube, position, itemCount)
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        when (holder) {
            is MovieVideoItemYouTubeViewHolder -> holder.clear()
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is MovieVideoItem.YouTube -> MOVIE_VIDEO_ITEM_YOU_TUBE
        }
}