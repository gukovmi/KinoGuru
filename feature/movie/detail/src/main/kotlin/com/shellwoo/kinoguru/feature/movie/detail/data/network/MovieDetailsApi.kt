package com.shellwoo.kinoguru.feature.movie.detail.data.network

import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsApi {

    @GET("movie/{movie_id}")
    suspend fun get(
        @Path("movie_id") id: Int,
        @Query("language") language: String,
    ): MovieDetailsModel
}