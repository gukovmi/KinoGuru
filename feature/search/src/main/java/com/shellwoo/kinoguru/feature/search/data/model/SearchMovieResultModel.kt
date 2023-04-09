package com.shellwoo.kinoguru.feature.search.data.model

import com.google.gson.annotations.SerializedName

data class SearchMovieResultModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var movies: ArrayList<SearchMovieModel> = arrayListOf()
)