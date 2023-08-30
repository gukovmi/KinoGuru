package com.shellwoo.kinoguru.feature.movie.search.ui

import androidx.recyclerview.widget.DiffUtil
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchItem
import javax.inject.Inject

class MovieSearchSuccessItemDiffUtilCallback @Inject constructor() : DiffUtil.ItemCallback<MovieSearchItem.Success>() {

    override fun areItemsTheSame(oldItem: MovieSearchItem.Success, newItem: MovieSearchItem.Success): Boolean =
        oldItem.value.id == newItem.value.id

    override fun areContentsTheSame(oldItem: MovieSearchItem.Success, newItem: MovieSearchItem.Success): Boolean {
        val oldSearchMovie = oldItem.value
        val newSearchMovie = newItem.value

        return oldSearchMovie.posterPath == newSearchMovie.posterPath
                && oldSearchMovie.title == newSearchMovie.title
                && oldSearchMovie.voteAverage == newSearchMovie.voteAverage
                && oldSearchMovie.overview == newSearchMovie.overview
    }
}