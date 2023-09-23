package com.shellwoo.kinoguru.feature.movie.detail.data.network

import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieVideosResultModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieVideosApi {

    @GET("movie/{movie_id}/videos")
    suspend fun getAll(
        @Path("movie_id") id: Int,
        @Query("language") language: String,
    ): MovieVideosResultModel
}