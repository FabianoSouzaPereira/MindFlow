package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.core.routes.ScreenRoute

@Composable
fun DrawerItem(navController: NavHostController, text: String) {
    var route: String = ""

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        when (text) {
            "Home" -> route = ScreenRoute.Home.route
            "Settings" -> route = ScreenRoute.Settings.route
            "Login" -> route = ScreenRoute.Login.route
        }

        TextButton(
            onClick = { navController.navigate(route) },
            colors = ButtonDefaults.buttonColors(),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}
