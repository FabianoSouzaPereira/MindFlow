package com.fabianospdev.mindflow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.core.helpers.TokenManager
import com.fabianospdev.mindflow.core.routes.navigateToHome
import com.fabianospdev.mindflow.core.routes.navigateToLogin
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val token = TokenManager.getToken(context)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        // Usando LaunchedEffect para manejar o delay e navegação
        LaunchedEffect(Unit) {
            delay(2000)

            if (!token.isNullOrEmpty()) {
                navigateToHome(navController)
            } else {
                navigateToLogin(navController)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...")
        }
    }
}
