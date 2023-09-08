package com.shellwoo.kinoguru.feature.profile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.setResultListener
import com.shellwoo.kinoguru.feature.profile.R
import com.shellwoo.kinoguru.feature.profile.di.ProfileComponentViewModel
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileState
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileViewModel
import com.shellwoo.kinoguru.shared.language.ui.LanguageNameConverter
import com.shellwoo.kinoguru.shared.language.ui.LanguageResultContract
import com.shellwoo.kinoguru.shared.theme.ui.ThemeNameConverter
import com.shellwoo.kinoguru.shared.theme.ui.ThemeResultContract
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

class ProfileFragment : BaseFragment(R.layout.profile_fragment) {

    private val componentViewModel: ProfileComponentViewModel by viewModels()

    private val viewModel: ProfileViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val requestManager: RequestManager by lazy { Glide.with(this) }
    private val languageNameConverter by lazy { LanguageNameConverter(requireContext()) }

    @Inject
    lateinit var themeNameConverter: ThemeNameConverter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        if (savedInstanceState == null) {
            viewModel.loadProfile()
        }
    }

    private fun initListeners() {
        setResultListener(LanguageResultContract, viewModel::selectLanguage)
        setResultListener(ThemeResultContract, viewModel::selectTheme)
        language.setOnClickListener { viewModel.openLanguageScreen() }
        theme.setOnClickListener { viewModel.openThemeScreen() }
    }

    private fun renderState(state: ProfileState) {
        when (state) {
            ProfileState.Initial -> renderInitialState()
            ProfileState.Loading -> renderLoadingState()
            is ProfileState.Content -> renderContentState(state)
        }
    }

    private fun renderInitialState() {
        toolbar.isVisible = false
        content.isVisible = false
        progressBar.isVisible = false
    }

    private fun renderLoadingState() {
        toolbar.isVisible = true
        content.isVisible = false
        progressBar.isVisible = true
    }

    private fun renderContentState(state: ProfileState.Content) {
        toolbar.isVisible = true
        content.isVisible = true
        progressBar.isVisible = false

        state.name?.let { name.setText(it) }
        state.email?.let { email.setText(it) }
        state.photoUrl?.toUri().let { photoUri ->
            requestManager.load(photoUri)
                .into(photo)
        }

        language.setText(languageNameConverter.toName(state.language))
        theme.setText(themeNameConverter.toName(state.theme))
    }
}