package com.fabianospdev.mindflow.features.home.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.Drawer
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.HomeCenterAlignedTopAppBar
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowHomeErrorComponent
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowHomeNoConnection
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowHomeTimeoutError
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowHomeUnauthorized
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowHomeUnknown
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowHomeValidationError
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowIdleComponent
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowLoadingComponent
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.ShowSuccessComponent
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState
import com.fabianospdev.mindflow.features.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state by viewModel.state.observeAsState(HomeState.HomeIdle)
    val context = LocalContext.current
    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    )

    val view = LocalView.current
    val insets = WindowInsetsCompat.toWindowInsetsCompat(view.rootWindowInsets)
    val statusBarHeight = with(LocalDensity.current) {
        insets.getInsets(WindowInsetsCompat.Type.statusBars()).top.toDp()
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.sunrise_ociano),
                        contentScale = ContentScale.FillBounds,
                        alpha = 0.8f
                    )
            ) {
                when (state) {
                    is HomeState.HomeLoading -> {
                        ShowLoadingComponent()
                    }

                    is HomeState.HomeIdle -> {
                        ShowIdleComponent(paddingValues)
                    }

                    is HomeState.HomeSuccess -> {
                        ShowSuccessComponent()
                    }

                    is HomeState.HomeError -> {
                        ShowHomeErrorComponent(viewModel, state)
                    }

                    is HomeState.HomeNoConnection -> {
                        ShowHomeNoConnection(state)
                    }

                    is HomeState.HomeTimeoutError -> {
                        ShowHomeTimeoutError(state)
                    }

                    is HomeState.HomeUnauthorized -> {
                        ShowHomeUnauthorized(state)
                    }

                    is HomeState.HomeValidationError -> {
                        ShowHomeValidationError(state)
                    }

                    else -> {
                        ShowHomeUnknown()
                    }
                }
            }
        }
    }
}

@Composable
fun PrivacyPolicySection(onPrivacyClick: () -> Unit) {
    Text(
        text = "Política de Privacidade",
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onPrivacyClick)
    )
}