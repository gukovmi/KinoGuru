package com.shellwoo.kinoguru.feature.splash.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shellwoo.kinoguru.design.resource.R
import com.shellwoo.kinoguru.feature.splash.presentation.SplashState

private const val LOGO_GROUP_ANIMATION_LABEL = "LOGO_ANIMATION_LABEL"
private const val LOGO_FLOAT_ANIMATION_LABEL = "LOGO_FLOAT_ANIMATION_LABEL"
private const val LOGO_MIN_RELATIVE_SIZE = 0.9f
private const val LOGO_MAX_RELATIVE_SIZE = 1f
private const val LOGO_SIZE_DP = 200
private const val LOGO_HALF_CYCLE_DURATION_IN_MILLIS = 1000

@Composable
fun SplashScreen(
    state: SplashState,
) {
    when (state) {
        SplashState.Initial -> Unit
        SplashState.Content -> SplashScreenContent()
    }
}

@Composable
private fun SplashScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val scale by rememberInfiniteTransition(label = LOGO_GROUP_ANIMATION_LABEL)
            .animateFloat(
                initialValue = LOGO_MIN_RELATIVE_SIZE,
                targetValue = LOGO_MAX_RELATIVE_SIZE,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = LOGO_HALF_CYCLE_DURATION_IN_MILLIS,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = LOGO_FLOAT_ANIMATION_LABEL
            )

        Image(
            modifier = Modifier
                .size(LOGO_SIZE_DP.dp)
                .scale(scale),
            painter = painterResource(id = R.drawable.glasses),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(SplashState.Content)
}