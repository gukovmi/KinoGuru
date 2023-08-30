package com.shellwoo.kinoguru.feature.movie.search.data.network

import com.shellwoo.kinoguru.feature.movie.search.data.model.MovieSearchResultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchApi {

    @GET("search/movie")
    suspend fun get(
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("page") page: Int?,
    ): MovieSearchResultModel
}