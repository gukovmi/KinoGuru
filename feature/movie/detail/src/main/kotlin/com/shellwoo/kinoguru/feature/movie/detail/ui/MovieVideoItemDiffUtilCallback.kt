package com.shellwoo.kinoguru.feature.movie.detail.ui

import androidx.recyclerview.widget.DiffUtil
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieVideoItem
import javax.inject.Inject

class MovieVideoItemDiffUtilCallback @Inject constructor() :
    DiffUtil.ItemCallback<MovieVideoItem>() {

    override fun areItemsTheSame(oldItem: MovieVideoItem, newItem: MovieVideoItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieVideoItem, newItem: MovieVideoItem): Boolean =
        oldItem.id == newItem.id
}