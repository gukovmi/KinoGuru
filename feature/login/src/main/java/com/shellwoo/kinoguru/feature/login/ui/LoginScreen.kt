package com.shellwoo.kinoguru.feature.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shellwoo.kinoguru.feature.login.R

@Composable
fun LoginScreen(
    onGoogleButtonClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = onGoogleButtonClick,
        ) {
            Image(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(R.drawable.google_logo),
                contentDescription = null,
            )

            Text(text = stringResource(R.string.login_google_button))
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen {}
}