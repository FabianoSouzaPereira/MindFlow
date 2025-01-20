package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState

@Composable
fun ShowHomeUnauthorized(state: HomeState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text("HomeUnauthorized")
    }
}