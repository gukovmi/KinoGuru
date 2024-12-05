package com.shellwoo.kinoguru.feature.language.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.presentation.observeAsNotNullableState
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.component.NO_CONTENT_LAYOUT_ID
import com.shellwoo.kinoguru.core.ui.ext.createComposeView
import com.shellwoo.kinoguru.core.ui.ext.setResult
import com.shellwoo.kinoguru.feature.language.di.LanguageComponentViewModel
import com.shellwoo.kinoguru.feature.language.presentation.LanguageViewModel
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.ui.LanguageNameConverter
import com.shellwoo.kinoguru.shared.language.ui.LanguageResultContract
import javax.inject.Inject

class LanguageFragment : BaseFragment(NO_CONTENT_LAYOUT_ID) {

    private val componentViewModel: LanguageComponentViewModel by viewModels()
    private val viewModel: LanguageViewModel by viewModels(factoryProducer = { viewModelFactory })

    @Inject
    lateinit var languageNameConverter: LanguageNameConverter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        createComposeView {
            val languages by viewModel.languages.observeAsNotNullableState()

            LanguageScreen(
                languages = languages,
                languageNameConverter = languageNameConverter::toName,
                onLanguageClick = ::selectLanguage,
                onNavigationIconClick = viewModel::close,
            )
        }

    private fun selectLanguage(language: Language) {
        setResult(LanguageResultContract, language)
        viewModel.close()
    }
}