package com.shellwoo.kinoguru.feature.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.feature.login.GoogleAuthClient
import com.shellwoo.kinoguru.feature.login.GoogleAuthVariant
import com.shellwoo.kinoguru.feature.login.GoogleSignInRequestIntentProvider
import com.shellwoo.kinoguru.feature.login.R
import com.shellwoo.kinoguru.feature.login.presentation.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    @Inject
    lateinit var googleSignInRequestIntentProvider: GoogleSignInRequestIntentProvider

    @Inject
    lateinit var googleAuthClient: GoogleAuthClient

    private val viewModel: LoginViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val requestSignInLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { activityResult ->
        googleSignIn(activityResult.data)
    }

    private fun googleSignIn(data: Intent?) {
        viewLifecycleOwner.lifecycleScope.launch { googleAuthClient.signIn(data) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        googleSignIn.setOnClickListener { viewModel.requestGoogleStandardSignIn() }
        viewModel.requestGoogleOneTapSignInEvent.observe(viewLifecycleOwner) { requestGoogleSignIn(GoogleAuthVariant.ONE_TAP) }
        viewModel.requestGoogleStandardSignInEvent.observe(viewLifecycleOwner) { requestGoogleSignIn(GoogleAuthVariant.STANDARD) }

        viewModel.requestGoogleOneTapSignIn()
    }

    private fun requestGoogleSignIn(authVariant: GoogleAuthVariant) {
        viewLifecycleOwner.lifecycleScope.launch {
            val authPendingIntent = googleSignInRequestIntentProvider.get(authVariant)
            val authIntentSenderRequest = IntentSenderRequest.Builder(authPendingIntent).build()
            requestSignInLauncher.launch(authIntentSenderRequest)
        }
    }
}