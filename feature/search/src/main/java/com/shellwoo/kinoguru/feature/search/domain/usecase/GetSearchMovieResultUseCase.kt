package com.shellwoo.kinoguru.feature.search.domain.usecase

import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import com.shellwoo.kinoguru.feature.search.domain.repository.SearchMovieResultRepository
import javax.inject.Inject

class GetSearchMovieResultUseCase @Inject constructor(
    private val repository: SearchMovieResultRepository,
) : suspend (String) -> SearchMovieResult by repository::get