package com.shellwoo.kinoguru.feature.search.ui

import androidx.recyclerview.widget.DiffUtil
import com.shellwoo.kinoguru.feature.search.presentation.SearchMovieItem
import javax.inject.Inject

class SearchMovieItemDiffUtilCallback @Inject constructor(private val successDiffUtilCallback: SearchMovieSuccessItemDiffUtilCallback) :
    DiffUtil.ItemCallback<SearchMovieItem>() {

    override fun areItemsTheSame(oldItem: SearchMovieItem, newItem: SearchMovieItem): Boolean =
        when {
            oldItem == SearchMovieItem.Loading &&
                    newItem == SearchMovieItem.Loading -> true
            oldItem is SearchMovieItem.Success &&
                    newItem is SearchMovieItem.Success -> successDiffUtilCallback.areItemsTheSame(oldItem, newItem)
            else -> false
        }

    override fun areContentsTheSame(oldItem: SearchMovieItem, newItem: SearchMovieItem): Boolean =
        when {
            oldItem == SearchMovieItem.Loading &&
                    newItem == SearchMovieItem.Loading -> true
            oldItem is SearchMovieItem.Success &&
                    newItem is SearchMovieItem.Success -> successDiffUtilCallback.areContentsTheSame(oldItem, newItem)
            else -> false
        }
}