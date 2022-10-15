package com.shellwoo.kinoguru.feature.profile.ui

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.showRetryCancelErrorDialog
import com.shellwoo.kinoguru.feature.profile.R
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileState
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileViewModel
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : BaseFragment(R.layout.profile_fragment) {

    private val viewModel: ProfileViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val requestManager: RequestManager by lazy { Glide.with(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.loadInitialData()
    }

    private fun renderState(state: ProfileState) {
        when (state) {
            ProfileState.Initial -> renderInitialState()
            ProfileState.Loading -> renderLoadingState()
            is ProfileState.Content -> renderContentState(state)
            ProfileState.InitialDataLoadingError -> renderInitialDataLoadingErrorState()
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

        state.name.let { name.text = getString(R.string.profile_name, it) }
        state.email.let { email.text = getString(R.string.profile_email, it) }
        state.photoUrl?.toUri().let { photoUri ->
            requestManager.load(photoUri)
                .into(photo)
        }
    }

    private fun renderInitialDataLoadingErrorState() {
        toolbar.isVisible = true
        content.isVisible = false
        progressBar.isVisible = false
        showRetryCancelErrorDialog(onRetryAction = viewModel::loadInitialData)
    }
}