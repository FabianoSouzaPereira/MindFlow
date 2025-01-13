package com.fabianospdev.mindflow.features.home.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.core.routes.ScreenRoute
import com.fabianospdev.mindflow.core.utils.LoadFontsFamily

@Composable
fun DrawerItem(navController: NavHostController, text: String, icon: ImageVector) {
    var route: String = ""

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 16.dp)
            .background(color = Color.Transparent),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        when (text) {
            "Home" -> route = ScreenRoute.Home.route
            "Settings" -> route = ScreenRoute.Settings.route
            "Login" -> route = ScreenRoute.Login.route
        }

        TextButton(
            onClick = { navController.navigate(route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
            ),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .background(color = Color.Transparent),
        ) {

            Row {
                Icon(
                    imageVector = icon,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    fontFamily = LoadFontsFamily.sourGummyFamily,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 2.dp, end = 4.dp, bottom = 6.dp)
                        .fillMaxWidth()
                        .background(color = Color.Transparent),
                )
            }
        }
    }
}
