package com.shellwoo.kinoguru.feature.search.data.network

import com.shellwoo.kinoguru.feature.search.data.model.SearchMovieResultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMovieApi {

    @GET("search/movie")
    suspend fun get(
        @Query("query") query: String,
    ): SearchMovieResultModel
}