package com.shellwoo.kinoguru.feature.movie.detail.domain.repository

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import com.shellwoo.kinoguru.shared.language.domain.entity.Language

interface MovieDetailsRepository {

    suspend fun get(id: Int, language: Language): MovieDetails
}