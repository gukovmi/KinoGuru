package com.shellwoo.kinoguru.feature.search.data.datasource

import com.shellwoo.kinoguru.feature.search.data.model.SearchMovieResultModel
import com.shellwoo.kinoguru.feature.search.data.network.SearchMovieApi
import javax.inject.Inject

class SearchMovieResultDataSource @Inject constructor(private val api: SearchMovieApi) {

    suspend fun get(query: String, page: Int?): SearchMovieResultModel =
        api.get(query, page)
}