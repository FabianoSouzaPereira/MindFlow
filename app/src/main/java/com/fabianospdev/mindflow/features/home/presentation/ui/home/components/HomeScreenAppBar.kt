package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAppBar() {
    TopAppBar(
        title = { Text("Home") },
    )
}
