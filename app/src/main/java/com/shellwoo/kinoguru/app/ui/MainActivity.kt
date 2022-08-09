package com.shellwoo.kinoguru.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shellwoo.kinoguru.R
import com.shellwoo.kinoguru.feature.splash.ui.SplashFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openSplashFragment()
        }
    }

    private fun openSplashFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, SplashFragment())
            .commit()
    }
}