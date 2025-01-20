package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import com.fabianospdev.mindflow.features.settings.presentation.viewmodel.SettingsViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
internal fun ShowSettingsIdle(
    isUsingFirebase: Boolean,
    viewModel: SettingsViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
            .paint(painterResource(id = R.drawable.sunrise_ociano), contentScale = ContentScale.FillBounds),
        contentAlignment = Alignment.Center
    ) {
        Text("SettingsIdle")
        if (isUsingFirebase) {
            val settings = GlobalSettingsFirestoreModel(
                maintenanceMode = true,
                defaultLanguage = "Portuguese2",
                privacyPolicyURL = "http://teste.comURL",
                termsOfServiceURL = "http://termsURL",
                appVersion = "1.0.1",
                featureToggle = true,
                supportContactEmail = "support@email.com",
                defaultTimezone = "EN",
                maxUploadSize = 6565665,
                analyticsEnabled = false,
                chatEnabled = false,
                darkMode = false
            )
            Button(onClick = {
                val currentUser = FirebaseAuth.getInstance().currentUser
                if (currentUser != null) {
                    viewModel.setSettings(settings)
                }

            }) {
                Text("SetSettings")
            }
        } else {
            val settings = GlobalSettingsRelationalModel(
                maintenanceMode = true,
                defaultLanguage = "Portuguese",
                privacyPolicyURL = "http://teste.comURL",
                termsOfServiceURL = "http://termsURL",
                appVersion = "1.0.0",
                featureToggle = true,
                supportContactEmail = "support@email.com",
                defaultTimezone = "EN",
                maxUploadSize = 6565665,
                analyticsEnabled = false,
                chatEnabled = false,
                darkMode = false
            )
            Button(onClick = { viewModel.setSettings(settings) }) {
                Text("SetSettings")
            }
        }
    }
}