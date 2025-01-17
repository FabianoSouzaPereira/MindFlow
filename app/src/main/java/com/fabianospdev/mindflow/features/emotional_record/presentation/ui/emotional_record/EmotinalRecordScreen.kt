package com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record.states.EmotionalRecordState
import com.fabianospdev.mindflow.features.emotional_record.presentation.viewmodel.EmotionalRecordViewModel
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.Drawer
import com.fabianospdev.mindflow.features.home.presentation.ui.home.components.HomeCenterAlignedTopAppBar
import kotlinx.coroutines.launch

@Composable
fun EmotionalRecordScreen(
    viewModel: EmotionalRecordViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state by viewModel.state.observeAsState(EmotionalRecordState.EmotionalRecordIdle)

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
                    is EmotionalRecordState.EmotionalRecordLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is EmotionalRecordState.EmotionalRecordIdle -> {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            color = Color.Transparent,
                            contentColor = Color.Transparent
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .background(color = Color.Transparent)
                                    .padding(
                                        top = 80.dp
                                    )
                            ) {
                                Text(
                                    text = "Emotional Record",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }

                    is EmotionalRecordState.EmotionalRecordSuccess -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("EmotionalRecordSuccess")
                        }
                    }

                    is EmotionalRecordState.EmotionalRecordError -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("EmotionalRecordError")
                        }
                    }

                    is EmotionalRecordState.EmotionalRecordNoConnection -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("EmotionalRecordNoConnection")
                        }
                    }

                    is EmotionalRecordState.EmotionalRecordTimeoutError -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("EmotionalRecordTimeoutError")
                        }
                    }

                    is EmotionalRecordState.EmotionalRecordUnknown -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("EmotionalRecordUnknown")
                        }
                    }

                    is EmotionalRecordState.EmotionalRecordValidationError -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("EmotionalRecordValidationError")
                        }
                    }

                    else -> {
                        EmotionalRecordState.EmotionalRecordUnknown("Error State Unknown")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmotionalRecordScreenPreview() {

    EmotionalRecordScreen(viewModel = hiltViewModel(), navController = rememberNavController())
}