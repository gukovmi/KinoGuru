package com.shellwoo.kinoguru.feature.search.ui

import androidx.recyclerview.widget.DiffUtil
import com.shellwoo.kinoguru.feature.search.presentation.SearchMovieItem
import javax.inject.Inject

class SearchMovieSuccessItemDiffUtilCallback @Inject constructor() : DiffUtil.ItemCallback<SearchMovieItem.Success>() {

    override fun areItemsTheSame(oldItem: SearchMovieItem.Success, newItem: SearchMovieItem.Success): Boolean =
        oldItem.value.id == newItem.value.id

    override fun areContentsTheSame(oldItem: SearchMovieItem.Success, newItem: SearchMovieItem.Success): Boolean {
        val oldSearchMovie = oldItem.value
        val newSearchMovie = newItem.value

        return oldSearchMovie.posterPath == newSearchMovie.posterPath
                && oldSearchMovie.title == newSearchMovie.title
                && oldSearchMovie.voteAverage == newSearchMovie.voteAverage
                && oldSearchMovie.overview == newSearchMovie.overview
    }
}