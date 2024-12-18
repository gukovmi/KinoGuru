package com.shellwoo.kinoguru.feature.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.shellwoo.kinoguru.core.ui.getClickInteractionSource
import com.shellwoo.kinoguru.design.resource.AppTheme
import com.shellwoo.kinoguru.design.uikit.AppBar
import com.shellwoo.kinoguru.feature.profile.R
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileState
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.design.resource.R as designResourceR

@Composable
fun ProfileScreen(
    state: ProfileState,
    languageNameConverter: (Language) -> String,
    themeNameConverter: (Theme) -> String,
    onLanguageClick: () -> Unit,
    onThemeClick: () -> Unit,
    onPhotoClick: () -> Unit,
) {
    when (state) {
        ProfileState.Initial -> Unit
        ProfileState.Loading -> ProfileScreenLoading()
        is ProfileState.Content -> ProfileScreenContent(
            state,
            languageNameConverter,
            themeNameConverter,
            onLanguageClick,
            onThemeClick,
            onPhotoClick,
        )
    }
}

@Composable
fun ProfileScreenLoading() {
    Column {
        ProfileAppBar()

        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreenContent(
    state: ProfileState.Content,
    languageNameConverter: (Language) -> String,
    themeNameConverter: (Theme) -> String,
    onLanguageClick: () -> Unit,
    onThemeClick: () -> Unit,
    onPhotoClick: () -> Unit,
) {
    Column {
        ProfileAppBar()

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            GlideImage(
                modifier = Modifier
                    .padding(16.dp)
                    .width(200.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = onPhotoClick),
                model = state.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                failure = placeholder(R.drawable.person),
            )

            if (state.name != null) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    enabled = false,
                    label = { Text(text = stringResource(R.string.profile_name_hint)) },
                    leadingIcon = {
                        Image(
                            painter = painterResource(R.drawable.person),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        )
                    },
                    onValueChange = {},
                    value = state.name,
                )
            }

            if (state.email != null) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    enabled = false,
                    label = { Text(text = stringResource(R.string.profile_email_hint)) },
                    leadingIcon = {
                        Image(
                            painter = painterResource(R.drawable.email),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        )
                    },
                    onValueChange = {},
                    value = state.email,
                )
            }

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .focusable(false),
                interactionSource = getClickInteractionSource(onLanguageClick),
                label = { Text(text = stringResource(R.string.profile_language_hint)) },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.language),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    )
                },
                trailingIcon = {
                    Image(
                        painter = painterResource(designResourceR.drawable.arrow_right),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                },
                onValueChange = {},
                readOnly = true,
                value = languageNameConverter(state.language),
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusable(false),
                interactionSource = getClickInteractionSource(onThemeClick),
                label = { Text(text = stringResource(R.string.profile_theme_hint)) },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.day_night),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    )
                },
                trailingIcon = {
                    Image(
                        painter = painterResource(designResourceR.drawable.arrow_right),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                },
                onValueChange = {},
                readOnly = true,
                value = themeNameConverter(state.theme),
            )
        }
    }
}

@Composable
fun ProfileAppBar() {
    AppBar(
        text = stringResource(R.string.profile_title),
        navigationIconParams = null,
    )
}

@PreviewLightDark
@Composable
fun ProfileScreenPreview() {
    val state = ProfileState.Content(
        name = "User User",
        email = "user@gmail.com",
        photoUrl = null,
        language = Language.ENGLISH,
        theme = Theme.DARK,
    )

    AppTheme {
        Surface {
            ProfileScreen(
                state = state,
                languageNameConverter = { "English" },
                themeNameConverter = { "Dark" },
                onLanguageClick = {},
                onThemeClick = {},
                onPhotoClick = {},
            )
        }
    }
}