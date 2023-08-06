package com.shellwoo.kinoguru.shared.onboarding.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.shellwoo.kinoguru.shared.onboarding.R
import kotlinx.android.synthetic.main.onboarding_dialog_fragment.*
import com.shellwoo.kinoguru.design.resource.R as designResourceR

const val DESCRIPTION_KEY = "DESCRIPTION_KEY"
private var Bundle.description: String
    get() = getString(DESCRIPTION_KEY) ?: ""
    set(value) = putString(DESCRIPTION_KEY, value)

class OnboardingDialogFragment : DialogFragment(R.layout.onboarding_dialog_fragment) {

    private var targetView: View? = null

    companion object {

        private const val ONBOARDING_DIALOG_TAG = "ONBOARDING_DIALOG_TAG"

        fun show(fragmentManager: FragmentManager, targetView: View, description: String) {
            fragmentManager.beginTransaction()
                .add(newInstance(description, targetView), ONBOARDING_DIALOG_TAG)
                .commit()
        }

        private fun newInstance(description: String, targetView: View): OnboardingDialogFragment =
            OnboardingDialogFragment().apply {
                arguments = Bundle().apply {
                    this.description = description
                }
                this.targetView = targetView
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, designResourceR.style.Dialog_Colored_Full_Transparent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            val targetView = targetView
            val description = arguments?.description
            if (targetView != null && description != null) {
                onboarding.setup(targetView, description) { close() }
                onboarding.setOnClickListener {
                    close()
                }
            }
        }
    }

    private fun close() {
        dismiss()
    }
}