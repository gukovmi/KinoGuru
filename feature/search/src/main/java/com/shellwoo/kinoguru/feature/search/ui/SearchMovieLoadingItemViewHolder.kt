package com.shellwoo.kinoguru.feature.search.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shellwoo.kinoguru.core.ui.inflate
import com.shellwoo.kinoguru.feature.search.R

class SearchMovieLoadingItemViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.search_movie_loading_item, false))