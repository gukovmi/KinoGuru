package com.shellwoo.kinoguru.feature.language.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetAllLanguagesUseCase
import javax.inject.Inject

class LanguageViewModel @Inject constructor(
    getAllLanguagesUseCase: GetAllLanguagesUseCase,
    private val router: LanguageRouter
) : ViewModel() {

    private val _languages = MutableLiveData(getAllLanguagesUseCase())
    val languages: LiveData<List<Language>> = _languages

    fun close() {
        router.close()
    }
}