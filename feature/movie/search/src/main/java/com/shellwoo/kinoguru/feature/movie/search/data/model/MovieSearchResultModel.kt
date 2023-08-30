package com.shellwoo.kinoguru.feature.movie.search.data.model

import com.google.gson.annotations.SerializedName

data class MovieSearchResultModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var movies: ArrayList<MovieSearchModel> = arrayListOf()
)