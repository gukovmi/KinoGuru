package com.shellwoo.kinoguru.feature.language.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.setResult
import com.shellwoo.kinoguru.feature.language.R
import com.shellwoo.kinoguru.feature.language.databinding.LanguageFragmentBinding
import com.shellwoo.kinoguru.feature.language.di.LanguageComponentViewModel
import com.shellwoo.kinoguru.feature.language.presentation.LanguageViewModel
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.ui.LanguageResultContract
import javax.inject.Inject

class LanguageFragment : BaseFragment(R.layout.language_fragment) {

    private val componentViewModel: LanguageComponentViewModel by viewModels()
    private val viewModel: LanguageViewModel by viewModels(factoryProducer = { viewModelFactory })
    private val binding by viewBinding(LanguageFragmentBinding::bind)

    @Inject
    lateinit var languageAdapter: LanguageAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { viewModel.close() }
        languageAdapter.setOnClickListener(::selectLanguage)
        viewModel.languages.observe(viewLifecycleOwner, ::renderLanguages)
    }

    private fun selectLanguage(language: Language) {
        setResult(LanguageResultContract, language)
        viewModel.close()
    }

    private fun renderLanguages(values: List<Language>) {
        languageAdapter.submitList(values)
        binding.languages.adapter = languageAdapter
    }
}