package com.fabianospdev.mindflow.features.home.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.Drawer
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.FeatureCard
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.HomeCenterAlignedTopAppBar
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState
import com.fabianospdev.mindflow.features.home.presentation.viewmodel.HomeViewModel
import com.fabianospdev.mindflow.shared.ui.components.ShowRetryButton
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
                    })
            },
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
                    .paint(painterResource(id = R.drawable.sunrise_ociano), contentScale = ContentScale.FillBounds)
            ) {
                when (state) {
                    is HomeState.HomeLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is HomeState.HomeIdle -> {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            color = Color.Transparent,
                            contentColor = Color.Transparent
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        top = paddingValues.calculateTopPadding(),
                                        bottom = paddingValues.calculateBottomPadding(),
                                        start = 16.dp,
                                        end = 16.dp
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = "Bem-vindo(a) ao MindFlow",
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    FeatureCard(
                                        title = "Registro Emocional",
                                        description = "Selecione suas emoções e registre seus pensamentos.",
                                        iconRes = R.drawable.mood_48dp,
                                        iconColor = MaterialTheme.colorScheme.primary,
                                        onClick = { /* Navegar para a tela de Registro Emocional */ }
                                    )
                                    FeatureCard(
                                        title = "Exercícios Guiados",
                                        description = "Meditação, respiração e outros exercícios.",
                                        iconRes = R.drawable.exercise_48dp,
                                        iconColor = MaterialTheme.colorScheme.primary,
                                        onClick = { /* Navegar para a tela de Exercícios Guiados */ }
                                    )
                                    FeatureCard(
                                        title = "Conteúdo Educativo",
                                        description = "Artigos e dicas sobre saúde mental.",
                                        iconRes = R.drawable.school_48dp,
                                        iconColor = MaterialTheme.colorScheme.primary,
                                        onClick = { /* Navegar para a tela de Conteúdo Educativo */ }
                                    )
                                    PrivacyPolicySection(onPrivacyClick = { /* Mostrar Política de Privacidade */ })
                                }
                            }
                        }
                    }


                    is HomeState.HomeSuccess -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("HomeSuccess")
                        }
                    }

                    is HomeState.HomeError -> {
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

                    is HomeState.HomeNoConnection -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("HomeNoConnection")
                        }
                    }

                    is HomeState.HomeTimeoutError -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("HomeTimeoutError")
                        }
                    }

                    is HomeState.HomeUnauthorized -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("HomeUnauthorized")
                        }
                    }

                    is HomeState.HomeValidationError -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("HomeValidationError")
                        }
                    }

                    else -> {
                        HomeState.HomeUnknown("Error State Unknown")
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