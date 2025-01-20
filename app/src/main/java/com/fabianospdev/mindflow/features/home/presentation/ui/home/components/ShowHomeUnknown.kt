package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.runtime.Composable
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState

@Composable
fun ShowHomeUnknown() {
    HomeState.HomeUnknown("Error State Unknown")
}