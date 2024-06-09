package com.shellwoo.kinoguru.shared.permission.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri.fromParts
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import com.shellwoo.kinoguru.core.ui.ext.getThemeColor
import com.shellwoo.kinoguru.shared.permission.R
import com.shellwoo.kinoguru.design.resource.R as designResR

private const val PACKAGE_SCHEME = "package"

fun Fragment.isPermissionGranted(permission: Permission): Boolean =
    checkSelfPermission(requireContext(), permission.value) == PERMISSION_GRANTED

fun Fragment.checkAndRequestPermission(
    permission: Permission,
    permissionLauncher: ActivityResultLauncher<String>,
    onPermissionResult: (Boolean) -> Unit,
) {

    when {
        isPermissionGranted(permission) -> {
            onPermissionResult(true)
        }

        shouldShowRequestPermissionRationale(permission.value) -> {
            showExplanationPermissionDialog(
                permission = permission,
                continueAction = { permissionLauncher.launch(permission.value) },
                cancelAction = { onPermissionResult(false) },
            )
        }

        !shouldShowRequestPermissionRationale(permission.value) -> {
            showSettingsPermissionDialog(
                permission = permission,
                cancelAction = { onPermissionResult(false) },
            )
        }

        else -> {
            permissionLauncher.launch(permission.value)
        }
    }
}

private fun Fragment.showExplanationPermissionDialog(
    permission: Permission,
    continueAction: () -> Unit,
    cancelAction: () -> Unit,
) {
    val title = getString(
        R.string.permission_dialog_title,
        getPermissionName(requireContext(), permission)
    )

    val iconDrawable = getDrawable(requireContext(), designResR.drawable.question)?.apply {
        setTint(getThemeColor(requireContext(), designResR.attr.colorPrimary))
    }

    val message = when (permission) {
        Permission.MICRO -> getString(R.string.explanation_permission_dialog_message_micro)
    }

    AlertDialog.Builder(requireContext())
        .setPositiveButton(getString(R.string.explanation_permission_dialog_continue)) { _, _ -> continueAction() }
        .setOnDismissListener { cancelAction() }
        .setTitle(title)
        .setIcon(iconDrawable)
        .setMessage(message)
        .create()
        .show()
}

private fun Fragment.showSettingsPermissionDialog(
    permission: Permission,
    cancelAction: () -> Unit,
) {
    val settingsIntent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        fromParts(PACKAGE_SCHEME, requireContext().packageName, null),
    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    val title = getString(
        R.string.permission_dialog_title,
        getPermissionName(requireContext(), permission)
    )

    val iconDrawable = getDrawable(requireContext(), designResR.drawable.settings)?.apply {
        setTint(getThemeColor(requireContext(), designResR.attr.colorPrimary))
    }

    val message = getString(
        R.string.settings_permission_dialog_message,
        getPermissionName(requireContext(), permission)
    )

    AlertDialog.Builder(requireContext())
        .setPositiveButton(getString(R.string.settings_permission_dialog_settings)) { _, _ ->
            startActivity(settingsIntent)
        }
        .setNegativeButton(getString(R.string.permission_dialog_cancel)) { _, _ -> cancelAction() }
        .setOnDismissListener { cancelAction() }
        .setTitle(title)
        .setIcon(iconDrawable)
        .setMessage(message)
        .create()
        .show()
}

private fun getPermissionName(context: Context, permission: Permission): String =
    when (permission) {
        Permission.MICRO -> context.getString(R.string.permission_micro_name)
    }