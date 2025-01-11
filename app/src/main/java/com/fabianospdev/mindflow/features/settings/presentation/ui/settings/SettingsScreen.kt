package com.fabianospdev.mindflow.features.settings.presentation.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states.SettingsState
import com.fabianospdev.mindflow.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.observeAsState(SettingsState.SettingsIdle)
    val context = LocalContext.current


    when (state) {
        is SettingsState.SettingsLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is SettingsState.SettingsIdle -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("SettingsIdle")
            }
        }
        is SettingsState.SettingsSuccess -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("SettingsSuccess")
            }
        }
        is SettingsState.SettingsError -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("SettingsError")
            }
        }
        is SettingsState.SettingsNoConnection -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("SettingsNoConnection ")
            }
        }
        is SettingsState.SettingsTimeoutError -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("SettingsTimeoutError")
            }
        }
        is SettingsState.SettingsUnauthorized -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("SettingsUnauthorized")
            }
        }
        is SettingsState.SettingsValidationError -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("SettingsValidationError")
            }
        }
        else -> {
            SettingsState.SettingsUnknown("Error State Unknown")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("Error State Unknown")
            }
        }
    }
}