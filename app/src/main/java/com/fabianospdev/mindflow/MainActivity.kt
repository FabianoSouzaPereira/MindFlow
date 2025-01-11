package com.fabianospdev.mindflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = applicationContext
            Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent, tonalElevation = 5.dp) {
                MindFlowTheme {
                    navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.SPLASH) {
                        composable(Routes.SPLASH) {
                            SplashScreen(navController = navController)
                        }
                        composable(Routes.LOGIN) {
                            LoginScreen(navController = navController)
                        }
                        composable(Routes.HOME) {
                            HomeScreen (navController = navController)
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