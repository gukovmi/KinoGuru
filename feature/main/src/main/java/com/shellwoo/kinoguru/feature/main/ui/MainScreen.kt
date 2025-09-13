package com.shellwoo.kinoguru.feature.main.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import com.shellwoo.kinoguru.design.resource.AppTheme
import com.shellwoo.kinoguru.feature.main.R
import com.shellwoo.kinoguru.feature.main.presentation.MainState
import com.shellwoo.kinoguru.feature.main.presentation.Tab

@Composable
internal fun MainScreen(
    state: MainState,
    onTabClick: (Tab) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            factory = { context ->
                FragmentContainerView(context).apply {
                    id = R.id.container
                }
            }
        )

        NavigationBar {
            state.tabs.forEach { tab ->
                NavigationBarItem(
                    selected = state.selectedTab == tab,
                    onClick = { onTabClick(tab) },
                    icon = { Icon(painterResource(tab.getIconResId()), null) },
                    label = { Text(stringResource(tab.getTextResId())) }
                )
            }
        }
    }
}

@DrawableRes
private fun Tab.getIconResId(): Int =
    when (this) {
        Tab.SEARCH -> R.drawable.ic_baseline_search_24
        Tab.PROFILE -> R.drawable.ic_baseline_person_24
    }

@StringRes
private fun Tab.getTextResId(): Int =
    when (this) {
        Tab.SEARCH -> R.string.search_navigation_tab
        Tab.PROFILE -> R.string.profile_navigation_tab
    }

@PreviewLightDark
@Composable
private fun MainScreenPreview() {
    AppTheme {
        Surface {
            MainScreen(MainState(Tab.SEARCH, Tab.entries), {})
        }
    }
}