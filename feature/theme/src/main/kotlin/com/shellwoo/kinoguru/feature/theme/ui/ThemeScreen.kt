package com.shellwoo.kinoguru.feature.theme.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.shellwoo.kinoguru.design.resource.AppTheme
import com.shellwoo.kinoguru.design.uikit.AppBar
import com.shellwoo.kinoguru.design.uikit.NavigationIcon
import com.shellwoo.kinoguru.design.uikit.NavigationIconParams
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.design.resource.R as designResourceR
import com.shellwoo.kinoguru.feature.theme.R as featureThemeR

@Composable
fun ThemeScreen(
    themes: List<Theme>,
    themeNameConverter: (Theme) -> String,
    onThemeClick: (Theme) -> Unit,
    onNavigationIconClick: () -> Unit,
) {
    Column {
        AppBar(
            text = stringResource(featureThemeR.string.theme_toolbar_title),
            navigationIconParams = NavigationIconParams(NavigationIcon.CLOSE, onNavigationIconClick),
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(themes) { theme ->
                ThemeItem(theme = theme, themeNameConverter = themeNameConverter, onClick = onThemeClick)
            }
        }
    }
}

@Composable
private fun ThemeItem(
    theme: Theme,
    themeNameConverter: (Theme) -> String,
    onClick: (Theme) -> Unit
) {
    Row(
        modifier = Modifier.clickable(onClick = { onClick(theme) }),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(30.dp),
            painter = painterResource(theme.getDrawableRes()),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )

        Text(
            modifier = Modifier.weight(1f),
            text = themeNameConverter(theme),
        )

        Image(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(designResourceR.drawable.arrow_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        )
    }
}

@DrawableRes
private fun Theme.getDrawableRes(): Int =
    when (this) {
        Theme.LIGHT -> featureThemeR.drawable.light
        Theme.DARK -> featureThemeR.drawable.dark
        Theme.AUTO_TIME -> featureThemeR.drawable.time
        Theme.FOLLOW_SYSTEM -> featureThemeR.drawable.settings
        Theme.AUTO_BATTERY -> featureThemeR.drawable.battery
        Theme.UNSPECIFIED -> throw IllegalArgumentException("Theme: $this is not available")
    }

@PreviewLightDark
@Composable
private fun ThemeScreenPreview() {
    AppTheme {
        Surface {
            ThemeScreen(
                themes = Theme.values().toList().minus(Theme.UNSPECIFIED),
                themeNameConverter = Theme::name,
                onThemeClick = {},
                onNavigationIconClick = {},
            )
        }
    }
}