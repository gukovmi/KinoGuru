package com.shellwoo.kinoguru.feature.movie.detail.domain.scenario

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieVideosRepository
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase
import javax.inject.Inject

class GetMovieVideosScenario @Inject constructor(
    private val movieVideosRepository: MovieVideosRepository,
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
) {

    suspend operator fun invoke(id: Int): List<MovieVideo> {
        val language = getCurrentLanguageUseCase()
        return movieVideosRepository.getAll(id, language)
    }
}