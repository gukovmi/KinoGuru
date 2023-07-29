package com.shellwoo.kinoguru.feature.profile.di

import android.content.Context
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileRouter

interface ProfileDeps {

    val context: Context
    val profileRouter: ProfileRouter
}