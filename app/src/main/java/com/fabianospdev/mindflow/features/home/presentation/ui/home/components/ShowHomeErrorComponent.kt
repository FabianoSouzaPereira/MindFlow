package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError
import com.fabianospdev.mindflow.features.home.presentation.ui.home.HomeError
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState
import com.fabianospdev.mindflow.features.home.presentation.viewmodel.HomeViewModel
import com.fabianospdev.mindflow.shared.ui.components.ShowRetryButton

@Composable
fun ShowHomeErrorComponent(viewModel: HomeViewModel, state: HomeState) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    )

    val errorMessage = when ((state as HomeState.HomeError).error) {
        HomeError.DataLoadFailed.toString() -> HomeError.DataLoadFailed.message
        HomeError.SectionNotAvailable.toString() -> HomeError.SectionNotAvailable.message
        else -> CommonError.UnknownError.message
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text("HomeError: $errorMessage")
    }

    ShowRetryButton(
        viewModel = viewModel,
        errorMessage = errorMessage,
        gradient = gradient,
        onRetry = {
            viewModel.getHome()
        }
    )
}