package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.core.utils.LoadFontsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCenterAlignedTopAppBar(navController: NavHostController, onMenuClick: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val sourGummyExpandedLight = LoadFontsFamily.sourGummyFamily.apply { R.font.sourgummy_expanded_light to FontWeight.Light }

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent,

            ),
        title = {
            Text(
                text = "MindFlow",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontFamily = LoadFontsFamily.sourGummyFamily,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = sourGummyExpandedLight,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Normal,
                    fontSize = 30.sp
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}
