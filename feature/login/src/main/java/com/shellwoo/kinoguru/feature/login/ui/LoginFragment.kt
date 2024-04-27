package com.shellwoo.kinoguru.feature.login.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.core.coroutines.launchTrying
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.showToast
import com.shellwoo.kinoguru.feature.login.GoogleAuthClient
import com.shellwoo.kinoguru.feature.login.GoogleAuthVariant
import com.shellwoo.kinoguru.feature.login.GoogleSignInRequestIntentProvider
import com.shellwoo.kinoguru.feature.login.R
import com.shellwoo.kinoguru.feature.login.databinding.LoginFragmentBinding
import com.shellwoo.kinoguru.feature.login.di.LoginComponentViewModel
import com.shellwoo.kinoguru.feature.login.presentation.LoginViewModel
import javax.inject.Inject

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    @Inject
    lateinit var googleSignInRequestIntentProvider: GoogleSignInRequestIntentProvider

    @Inject
    lateinit var googleAuthClient: GoogleAuthClient

    private val componentViewModel: LoginComponentViewModel by viewModels()

    private val viewModel: LoginViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val binding by viewBinding(LoginFragmentBinding::bind)

    private val requestSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult(), ::handleSignInResult)

    private fun handleSignInResult(activityResult: ActivityResult) {
        if (activityResult.resultCode != RESULT_OK) return

        viewLifecycleOwner.lifecycleScope.launchTrying(
            errorHandler = {
                viewModel.handleSignInError()
            },
            block = {
                googleAuthClient.signIn(activityResult.data)
                viewModel.openMainScreen()
            },
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.googleSignIn.setOnClickListener { viewModel.requestGoogleStandardSignIn() }
        observeViewModel()

        viewModel.requestGoogleOneTapSignIn()
    }

    private fun observeViewModel() {
        viewModel.requestGoogleOneTapSignInEvent.observe(viewLifecycleOwner) { requestGoogleSignIn(GoogleAuthVariant.ONE_TAP) }
        viewModel.requestGoogleStandardSignInEvent.observe(viewLifecycleOwner) { requestGoogleSignIn(GoogleAuthVariant.STANDARD) }
        viewModel.signInErrorEvent.observe(viewLifecycleOwner) { showSingInErrorMessage() }
    }

    private fun requestGoogleSignIn(authVariant: GoogleAuthVariant) {
        viewLifecycleOwner.lifecycleScope.launchTrying(
            errorHandler = { viewModel.handleSignInError() },
            block = {
                val authPendingIntent = googleSignInRequestIntentProvider.get(authVariant)
                val authIntentSenderRequest = IntentSenderRequest.Builder(authPendingIntent).build()
                requestSignInLauncher.launch(authIntentSenderRequest)
            },
        )
    }

    private fun showSingInErrorMessage() {
        showToast(getString(R.string.login_sign_in_error))
    }
}