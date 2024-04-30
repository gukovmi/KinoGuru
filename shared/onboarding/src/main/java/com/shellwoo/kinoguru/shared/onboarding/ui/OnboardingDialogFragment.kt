package com.shellwoo.kinoguru.shared.onboarding.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.shared.onboarding.R
import com.shellwoo.kinoguru.shared.onboarding.databinding.OnboardingDialogFragmentBinding
import com.shellwoo.kinoguru.design.resource.R as designResourceR

const val DESCRIPTION_KEY = "DESCRIPTION_KEY"
private var Bundle.description: String
    get() = getString(DESCRIPTION_KEY) ?: ""
    set(value) = putString(DESCRIPTION_KEY, value)

class OnboardingDialogFragment : DialogFragment(R.layout.onboarding_dialog_fragment) {

    private var targetView: View? = null
    private val binding by viewBinding(OnboardingDialogFragmentBinding::bind)

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
                binding.onboarding.setup(targetView, description) { close() }
                binding.onboarding.setOnClickListener {
                    close()
                }
            }
        }
    }

    private fun close() {
        dismiss()
    }
}