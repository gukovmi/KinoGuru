package com.shellwoo.kinoguru.feature.search.data.datasource

import com.shellwoo.kinoguru.feature.search.data.model.SearchMovieResultModel
import com.shellwoo.kinoguru.feature.search.data.network.SearchMovieApi

class SearchMovieResultDataSource(private val api: SearchMovieApi) {

    suspend fun get(query: String, page: Int?): SearchMovieResultModel =
        api.get(query, page)
}