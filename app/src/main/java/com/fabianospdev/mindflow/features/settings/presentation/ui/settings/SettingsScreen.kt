package com.fabianospdev.mindflow.features.settings.presentation.ui.settings

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.Drawer
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.HomeCenterAlignedTopAppBar
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsError
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsLoading
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsNoConnection
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsSuccess
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsTimeoutError
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsUnauthorized
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsUnknown
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components.ShowSettingsValidationError
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states.SettingsState
import com.fabianospdev.mindflow.features.settings.presentation.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.observeAsState(SettingsState.SettingsLoading)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getSettings()
    }

    /** Observing the ViewModel state **/
    val isUsingFirebase by viewModel.isUsingFirebase.collectAsState()


    val globalSettings = viewModel.globalSettings.collectAsState(initial = null).value
    Log.d("Firebase", "val globalSettings = $globalSettings")


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val view = LocalView.current
    val insets = WindowInsetsCompat.toWindowInsetsCompat(view.rootWindowInsets)
    val statusBarHeight = with(LocalDensity.current) {
        insets.getInsets(WindowInsetsCompat.Type.statusBars()).top.toDp()
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Drawer(navController, statusBarHeight)
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HomeCenterAlignedTopAppBar(
                    navController = navController,
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ) { paddingValues ->

            when (state) {
                is SettingsState.SettingsLoading -> {
                    ShowSettingsLoading()
                }

                is SettingsState.SettingsIdle -> {}

                is SettingsState.SettingsSuccess -> {
                    Log.d("Firebase", "WHEN STATE is SUCCESS -> $globalSettings")

                    ShowSettingsSuccess(
                        globalSettings = globalSettings,
                        paddingValues = PaddingValues(16.dp),
                        onGetSettings = { viewModel.getSettings() },
                        onToggleMaintenanceMode = { viewModel.setMaintenanceMode(it) },
                        onToggleFirebaseMode = { viewModel.toggleFirebaseUsage(it) },
                        onToggleAnalyticsEnabled = { viewModel.setAnalyticsEnabled(it) },
                        onToggleChatEnabled = { viewModel.setChatEnabled(it) },
                        onToggleDarkMode = { viewModel.setDarkMode(it) }
                    )
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
    }
}