package com.fabianospdev.mindflow.features.settings.presentation.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsError
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsIdle
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsLoading
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsNoConnection
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsSuccess
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsTimeoutError
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsUnauthorized
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsUnknown
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsValidationError
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states.SettingsState
import com.fabianospdev.mindflow.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.observeAsState(SettingsState.SettingsIdle)
    val context = LocalContext.current

    /** Observing the ViewModel state **/
    val isUsingFirebase by viewModel.isUsingFirebase.collectAsState()

    when (state) {
        is SettingsState.SettingsLoading -> {
            ShowSettingsLoading()
        }

        is SettingsState.SettingsIdle -> {
            ShowSettingsIdle(isUsingFirebase, viewModel)
        }

        is SettingsState.SettingsSuccess -> {
            ShowSettingsSuccess()
        }

        is SettingsState.SettingsError -> {
            ShowSettingsError()
        }

        is SettingsState.SettingsNoConnection -> {
            ShowSettingsNoConnection()
        }

        is SettingsState.SettingsTimeoutError -> {
            ShowSettingsTimeoutError()
        }

        is SettingsState.SettingsUnauthorized -> {
            ShowSettingsUnauthorized()
        }

        is SettingsState.SettingsValidationError -> {
            ShowSettingsValidationError()
        }

        else -> {
            ShowSettingsUnknown()
        }
    }
}