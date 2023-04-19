package com.shellwoo.kinoguru.feature.search.data.repository

import com.shellwoo.kinoguru.feature.search.data.converter.SearchMovieResultModelConverter
import com.shellwoo.kinoguru.feature.search.data.datasource.SearchMovieResultDataSource
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import com.shellwoo.kinoguru.feature.search.domain.repository.SearchMovieResultRepository
import javax.inject.Inject

class SearchMovieResultRepositoryImpl @Inject constructor(
    private val dataSource: SearchMovieResultDataSource,
    private val converter: SearchMovieResultModelConverter,
) : SearchMovieResultRepository {

    override suspend fun get(query: String, page: Int?): SearchMovieResult =
        dataSource.get(query, page).let(converter::from)
}