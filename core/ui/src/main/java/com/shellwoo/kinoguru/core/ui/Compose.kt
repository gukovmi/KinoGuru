package com.shellwoo.kinoguru.core.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun getClickInteractionSource(onClick: () -> Unit) = remember { MutableInteractionSource() }.also { interactionSource ->
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                onClick()
            }
        }
    }
}