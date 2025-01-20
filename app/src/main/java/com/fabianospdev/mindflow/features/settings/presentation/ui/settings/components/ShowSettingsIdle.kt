package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings.GlobalSettingsFirestoreModel

@Composable
internal fun ShowSettingsIdle(
    globalSettings: GlobalSettingsFirestoreModel,
    paddingValues: PaddingValues,
    isUsingFirebase: Boolean,
    onToggleMaintenanceMode: (Boolean) -> Unit,
    onToggleFirebaseMode: (Boolean) -> Unit,
    onToggleAnalyticsEnabled: (Boolean) -> Unit,
    onToggleChatEnabled: (Boolean) -> Unit,
    onToggleDarkMode: (Boolean) -> Unit
) {
    var checkedMaintenanceMode by remember { mutableStateOf(globalSettings.maintenanceMode) }
    var checkedFirebaseMode by remember { mutableStateOf(isUsingFirebase) }
    var checkedAnalyticsEnabled by remember { mutableStateOf(globalSettings.analyticsEnabled) }
    var checkedChatEnabled by remember { mutableStateOf(globalSettings.chatEnabled) }
    var checkedDarkMode by remember { mutableStateOf(globalSettings.darkMode) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.8f))
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            ),
        contentAlignment = Alignment.TopStart
    ) {
        Column {
            // Maintenance Mode Switch
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Maintenance Mode",
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
                Switch(
                    checked = checkedMaintenanceMode,
                    onCheckedChange = {
                        checkedMaintenanceMode = it
                        onToggleMaintenanceMode(it)
                    }
                )
            }

            // Firebase Mode Switch
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Firebase Mode",
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
                Switch(
                    checked = checkedFirebaseMode,
                    onCheckedChange = {
                        checkedFirebaseMode = it
                        onToggleFirebaseMode(it)
                    }
                )
            }

            // Analytics Enabled Switch
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Analytics Enabled",
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
                Switch(
                    checked = checkedAnalyticsEnabled,
                    onCheckedChange = {
                        checkedAnalyticsEnabled = it
                        onToggleAnalyticsEnabled(it)
                    }
                )
            }

            // Chat Enabled Switch
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Chat Enabled",
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
                Switch(
                    checked = checkedChatEnabled,
                    onCheckedChange = {
                        checkedChatEnabled = it
                        onToggleChatEnabled(it)
                    }
                )
            }

            // Dark Mode Switch
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dark Mode",
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
                Switch(
                    checked = checkedDarkMode,
                    onCheckedChange = {
                        checkedDarkMode = it
                        onToggleDarkMode(it)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ShowSettingsPreview() {
    val paddingValues = PaddingValues(16.dp)
    val isUsingFirebase = true
    val globalSettingsFirestoreModel = GlobalSettingsFirestoreModel(
        maintenanceMode = false,
        defaultLanguage = "",
        privacyPolicyURL = "",
        termsOfServiceURL = "",
        appVersion = "",
        featureToggle = true,
        supportContactEmail = "",
        defaultTimezone = "",
        maxUploadSize = 0L,
        analyticsEnabled = true,
        chatEnabled = false,
        darkMode = false,
    )

    ShowSettingsIdle(
        globalSettings = globalSettingsFirestoreModel,
        paddingValues = paddingValues,
        isUsingFirebase = isUsingFirebase,
        onToggleMaintenanceMode = {},
        onToggleFirebaseMode = {},
        onToggleAnalyticsEnabled = {},
        onToggleChatEnabled = {},
        onToggleDarkMode = {},
    )
}
