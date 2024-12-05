package com.shellwoo.kinoguru.feature.language.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.shellwoo.kinoguru.design.resource.AppTheme
import com.shellwoo.kinoguru.design.uikit.AppBar
import com.shellwoo.kinoguru.design.uikit.NavigationIcon
import com.shellwoo.kinoguru.design.uikit.NavigationIconParams
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.design.resource.R as designResourceR
import com.shellwoo.kinoguru.feature.language.R as featureLanguageR

@Composable
fun LanguageScreen(
    languages: List<Language>,
    languageNameConverter: (Language) -> String,
    onLanguageClick: (Language) -> Unit,
    onNavigationIconClick: () -> Unit,
) {
    Column {
        AppBar(
            text = stringResource(featureLanguageR.string.language_toolbar_title),
            navigationIconParams = NavigationIconParams(NavigationIcon.CLOSE, onNavigationIconClick),
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(languages) { language ->
                LanguageItem(language, languageNameConverter, onLanguageClick)
            }
        }
    }
}

@Composable
private fun LanguageItem(
    language: Language,
    languageNameConverter: (Language) -> String,
    onClick: (Language) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(language) }),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(width = 35.dp, height = 30.dp)
                .border(1.dp, MaterialTheme.colorScheme.onBackground)
                .padding(1.dp),
            painter = painterResource(language.getFlagRes()),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Text(
            modifier = Modifier.weight(1f),
            text = languageNameConverter(language),
        )

        Image(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(designResourceR.drawable.arrow_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }
}

@DrawableRes
private fun Language.getFlagRes(): Int =
    when (this) {
        Language.ENGLISH -> featureLanguageR.drawable.united_kingdom_flag
        Language.RUSSIAN -> featureLanguageR.drawable.russia_flag
    }

@PreviewLightDark
@Composable
private fun LanguageScreenPreview() {
    AppTheme {
        Surface {
            LanguageScreen(
                languages = Language.values().toList(),
                languageNameConverter = Language::name,
                onLanguageClick = {},
                onNavigationIconClick = {},
            )
        }
    }
}