package com.shellwoo.kinoguru.feature.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val router: LoginRouter,
) : ViewModel() {

    private val _requestGoogleOneTapSignInEvent = SingleLiveEvent<Unit>()
    val requestGoogleOneTapSignInEvent: LiveData<Unit> = _requestGoogleOneTapSignInEvent

    private val _requestGoogleStandardSignInEvent = SingleLiveEvent<Unit>()
    val requestGoogleStandardSignInEvent: LiveData<Unit> = _requestGoogleStandardSignInEvent

    private val _signInErrorEvent = SingleLiveEvent<Unit>()
    val signInErrorEvent: LiveData<Unit> = _signInErrorEvent

    fun requestGoogleOneTapSignIn() {
        _requestGoogleOneTapSignInEvent(Unit)
    }

    fun requestGoogleStandardSignIn() {
        _requestGoogleStandardSignInEvent(Unit)
    }

    fun handleSignInError() {
        _signInErrorEvent(Unit)
    }

    fun openMainScreen() {
        router.openMainScreen()
    }
}