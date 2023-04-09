package com.shellwoo.kinoguru.feature.search.domain.repository

import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult

interface SearchMovieResultRepository {

    suspend fun get(query: String): SearchMovieResult
}