package com.shellwoo.kinoguru.feature.movie.search.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shellwoo.kinoguru.core.ui.ext.inflate
import com.shellwoo.kinoguru.feature.movie.search.R

class MovieSearchLoadingItemViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.movie_search_loading_item, false))