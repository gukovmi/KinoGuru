package com.shellwoo.kinoguru.feature.movie.detail.data.model

import com.google.gson.annotations.SerializedName

data class MovieVideosResultModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("results") val results: ArrayList<MovieVideoModel>,
)