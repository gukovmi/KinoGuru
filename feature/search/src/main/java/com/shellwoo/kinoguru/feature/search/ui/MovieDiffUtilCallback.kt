package com.shellwoo.kinoguru.feature.search.ui

import androidx.recyclerview.widget.DiffUtil
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<SearchMovie>() {

    override fun areItemsTheSame(oldItem: SearchMovie, newItem: SearchMovie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchMovie, newItem: SearchMovie): Boolean =
        oldItem.posterPath == newItem.posterPath
                && oldItem.title == newItem.title
                && oldItem.voteAverage == newItem.voteAverage
                && oldItem.overview == newItem.overview
}