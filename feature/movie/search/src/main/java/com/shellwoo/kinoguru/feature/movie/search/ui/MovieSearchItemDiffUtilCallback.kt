package com.shellwoo.kinoguru.feature.movie.search.ui

import androidx.recyclerview.widget.DiffUtil
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchItem
import javax.inject.Inject

class MovieSearchItemDiffUtilCallback @Inject constructor(private val successDiffUtilCallback: MovieSearchSuccessItemDiffUtilCallback) :
    DiffUtil.ItemCallback<MovieSearchItem>() {

    override fun areItemsTheSame(oldItem: MovieSearchItem, newItem: MovieSearchItem): Boolean =
        when {
            oldItem == MovieSearchItem.Loading &&
                    newItem == MovieSearchItem.Loading -> true
            oldItem is MovieSearchItem.Success &&
                    newItem is MovieSearchItem.Success -> successDiffUtilCallback.areItemsTheSame(oldItem, newItem)
            else -> false
        }

    override fun areContentsTheSame(oldItem: MovieSearchItem, newItem: MovieSearchItem): Boolean =
        when {
            oldItem == MovieSearchItem.Loading &&
                    newItem == MovieSearchItem.Loading -> true
            oldItem is MovieSearchItem.Success &&
                    newItem is MovieSearchItem.Success -> successDiffUtilCallback.areContentsTheSame(oldItem, newItem)
            else -> false
        }
}