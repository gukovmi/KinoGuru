package com.shellwoo.kinoguru.core.ui.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Modifier.clickableUnbounded(
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier =
    clickable(
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick,
        indication = ripple(bounded = false)
    )