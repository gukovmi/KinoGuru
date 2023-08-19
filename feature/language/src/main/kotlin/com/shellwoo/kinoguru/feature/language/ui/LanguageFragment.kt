package com.shellwoo.kinoguru.feature.language.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.setResult
import com.shellwoo.kinoguru.feature.language.R
import com.shellwoo.kinoguru.feature.language.di.LanguageComponentViewModel
import com.shellwoo.kinoguru.feature.language.presentation.LanguageViewModel
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.ui.LanguageResultContract
import kotlinx.android.synthetic.main.language_fragment.*
import javax.inject.Inject

class LanguageFragment : BaseFragment(R.layout.language_fragment) {

    private val componentViewModel: LanguageComponentViewModel by viewModels()
    private val viewModel: LanguageViewModel by viewModels(factoryProducer = { viewModelFactory })

    @Inject
    lateinit var languageAdapter: LanguageAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { viewModel.close() }
        languageAdapter.setOnClickListener(::selectLanguage)
        viewModel.languages.observe(viewLifecycleOwner, ::renderLanguages)
    }

    private fun selectLanguage(language: Language) {
        setResult(LanguageResultContract, language)
        viewModel.close()
    }

    private fun renderLanguages(values: List<Language>) {
        languageAdapter.submitList(values)
        languages.adapter = languageAdapter
    }
}