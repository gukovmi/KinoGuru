package com.shellwoo.kinoguru.feature.search.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shellwoo.kinoguru.feature.search.presentation.SearchMovieItem
import javax.inject.Inject

class SearchMovieItemAdapter @Inject constructor(diffUtilCallback: SearchMovieItemDiffUtilCallback) :
    ListAdapter<SearchMovieItem, RecyclerView.ViewHolder>(diffUtilCallback) {

    private companion object {

        const val LOADING_VIEW_TYPE = 0
        const val SUCCESS_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LOADING_VIEW_TYPE -> SearchMovieLoadingItemViewHolder(parent)
            SUCCESS_VIEW_TYPE -> SearchMovieSuccessItemViewHolder(parent)
            else -> error("ViewType: $viewType is invalid")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchMovieSuccessItemViewHolder -> holder.bind(getItem(position) as SearchMovieItem.Success)
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            SearchMovieItem.Loading -> LOADING_VIEW_TYPE
            is SearchMovieItem.Success -> SUCCESS_VIEW_TYPE
        }
}