package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Drawer(navController: NavHostController, statusBarHeight: Dp) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topPadding = statusBarHeight + 56.dp

    RightDrawer(
        drawerState = drawerState,
        content = {},
        drawerContent = {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = topPadding, end = 0.dp)
                    .background(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f))
                    .border(0.1.dp, MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)),
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(color = Color.Transparent),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Column(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .wrapContentSize()
                            .then(
                                Modifier.widthIn(max = 200.dp)
                            )
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        DrawerItem(navController, text = "Home", icon = Icons.Filled.Home)
                        Spacer(modifier = Modifier.height(2.dp))
                        DrawerItem(navController, text = "Settings", icon = Icons.Filled.Settings)
                        Spacer(modifier = Modifier.height(2.dp))
                        DrawerItem(navController, text = "Login", icon = Icons.Filled.MailOutline)
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        },
    )
}
