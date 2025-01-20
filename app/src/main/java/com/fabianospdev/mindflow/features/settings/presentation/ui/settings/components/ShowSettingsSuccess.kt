package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fabianospdev.mindflow.features.settings.presentation.viewmodel.SettingsViewModel
import kotlinx.coroutines.delay

@Composable
internal fun ShowSettingsSuccess(viewModel: SettingsViewModel) {
    LaunchedEffect(Unit) {
        delay(2000)
        viewModel.setIdleState()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("SettingsSuccess")

    }
}