package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity

@Composable
internal fun ShowSettingsSuccess(
    globalSettings: GlobalSettingsEntity?,
    paddingValues: PaddingValues,
    onGetSettings: () -> Unit,
    onToggleMaintenanceMode: (Boolean) -> Unit,
    onToggleFirebaseMode: (Boolean) -> Unit,
    onToggleAnalyticsEnabled: (Boolean) -> Unit,
    onToggleChatEnabled: (Boolean) -> Unit,
    onToggleDarkMode: (Boolean) -> Unit
) {

    LaunchedEffect(Unit) {
        onGetSettings()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.8f))
            .padding(
                top = paddingValues.calculateTopPadding() + 56.dp,
                bottom = paddingValues.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            ),
        contentAlignment = Alignment.TopStart
    ) {
        Column {
            SettingsSwitchRow(
                title = "Maintenance Mode",
                isChecked = globalSettings!!.maintenanceMode,
                onToggle = { onToggleMaintenanceMode(it) }
            )
            SettingsSwitchRow(
                title = "Firebase Mode",
                isChecked = globalSettings.featureToggle,
                onToggle = { onToggleFirebaseMode(it) }
            )
            SettingsSwitchRow(
                title = "Analytics Enabled",
                isChecked = globalSettings.analyticsEnabled,
                onToggle = { onToggleAnalyticsEnabled(it) }
            )
            SettingsSwitchRow(
                title = "Chat Enabled",
                isChecked = globalSettings.chatEnabled,
                onToggle = { onToggleChatEnabled(it) }
            )
            SettingsSwitchRow(
                title = "Dark Mode",
                isChecked = globalSettings.darkMode,
                onToggle = { onToggleDarkMode(it) }
            )
        }
    }

}