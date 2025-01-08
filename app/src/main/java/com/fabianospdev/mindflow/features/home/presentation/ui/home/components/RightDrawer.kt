package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RightDrawer(
    drawerState: DrawerState,
    content: @Composable () -> Unit,
    drawerContent: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        drawerContent()

        AnimatedVisibility(
            visible = drawerState.isOpen,
            enter = slideInHorizontally { it },
            exit = slideOutHorizontally { it }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .align(Alignment.CenterEnd)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                drawerContent()
            }
        }

        if (drawerState.isOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.32f))
                    .clickable {
                        scope.launch { drawerState.close() }
                    }
            )
        }
    }
}
