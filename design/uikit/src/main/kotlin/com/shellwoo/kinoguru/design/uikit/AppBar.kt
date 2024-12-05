package com.shellwoo.kinoguru.design.uikit

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.shellwoo.kinoguru.core.ui.ext.clickableUnbounded
import com.shellwoo.kinoguru.design.resource.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    text: String,
    navigationIconParams: NavigationIconParams?,
) {
    TopAppBar(
        modifier = Modifier
            .zIndex(Float.MAX_VALUE)
            .shadow(2.dp),
        title = { Text(text = text) },
        navigationIcon = {
            if (navigationIconParams != null) {
                Image(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickableUnbounded(onClick = navigationIconParams.onIconClick),
                    painter = painterResource(navigationIconParams.icon.drawableRes),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    contentDescription = null,
                )
            }
        },
    )
}

data class NavigationIconParams(
    val icon: NavigationIcon,
    val onIconClick: () -> Unit,
)

enum class NavigationIcon(@DrawableRes val drawableRes: Int) {
    BACK(R.drawable.arrow_left),
    CLOSE(R.drawable.close),
}