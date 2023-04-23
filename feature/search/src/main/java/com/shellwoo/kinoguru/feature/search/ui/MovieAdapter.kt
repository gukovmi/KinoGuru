package com.shellwoo.kinoguru.feature.search.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovie

class MovieAdapter : ListAdapter<SearchMovie, MovieViewHolder>(MovieDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}