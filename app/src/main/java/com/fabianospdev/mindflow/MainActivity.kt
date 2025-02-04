package com.fabianospdev.mindflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fabianospdev.mindflow.core.routes.Routes
import com.fabianospdev.mindflow.features.home.presentation.ui.home.HomeScreen
import com.fabianospdev.mindflow.features.login.presentation.ui.login.LoginScreen
import com.fabianospdev.mindflow.features.login.presentation.ui.theme.MindFlowTheme
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = applicationContext
            val mainViewModel: MainViewModel = hiltViewModel()
            val isDarkMode by mainViewModel.isDarkMode.collectAsState()
            // val recreateAction = rememberUpdatedState(::recreate)
            val lifecycleOwner = LocalLifecycleOwner.current

            LaunchedEffect(mainViewModel.recreateEvent) {
                Log.d("MainActivity", "Iniciando coleta de recreateEvent")
                lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    mainViewModel.recreateEvent.collectLatest {
                        Log.d("MainActivity", "Evento recreateActivity recebido")

                        val intent = Intent(context, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        context.startActivity(intent)
                        (context as? Activity)?.finish()
                    }
                }
            }

            MindFlowTheme(darkTheme = isDarkMode) {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent, tonalElevation = 5.dp) {
                    navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.SPLASH) {
                        composable(Routes.SPLASH) {
                            SplashScreen(navController = navController)
                        }
                        composable(Routes.LOGIN) {
                            LoginScreen(navController = navController)
                        }
                        composable(Routes.HOME) {
                            HomeScreen(navController = navController)
                        }
                        composable(Routes.SETTINGS) {
                            SettingsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}