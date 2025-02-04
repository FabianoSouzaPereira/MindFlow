package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabianospdev.mindflow.R
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
    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var serverUri by remember { mutableStateOf(globalSettings!!.serverAddress.serverUri) }
    var serverPort by remember { mutableStateOf(globalSettings!!.serverAddress.serverPort.toString()) }
    var serverUser by remember { mutableStateOf(globalSettings!!.serverAddress.serverUser) }
    var serverPassword by remember { mutableStateOf(globalSettings!!.serverAddress.serverPassword) }
    
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
                title = stringResource(R.string.maintenance_mode),
                isChecked = globalSettings!!.maintenanceMode,
                onToggle = { onToggleMaintenanceMode(it) }
            )
            SettingsSwitchRow(
                title = stringResource(R.string.firebase_mode),
                isChecked = globalSettings.featureToggle,
                onToggle = { onToggleFirebaseMode(it) }
            )
            if (!globalSettings.featureToggle) {

                TextField(
                    value = serverUri,
                    onValueChange = {
                        serverUri = it
                        globalSettings.serverAddress.serverUri = it
                    },
                    label = { Text(stringResource(R.string.server_uri)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 6.dp, 20.dp, 20.dp)
                        .border(
                            dimensionResource(R.dimen.textfield_border_size),
                            MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .focusRequester(focusRequester1),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.None
                    ),
                    shape = RoundedCornerShape(25),
                    placeholder = { Text(stringResource(R.string.enter_server_address)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequester2.requestFocus()
                        }
                    ),
                )

                TextField(
                    value = serverPort,
                    onValueChange = {
                        serverPort = it
                        globalSettings.serverAddress.serverPort = it.toIntOrNull() ?: 0
                    },
                    label = { Text(stringResource(R.string.server_port)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 6.dp, 20.dp, 20.dp)
                        .border(
                            dimensionResource(R.dimen.textfield_border_size),
                            MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .focusRequester(focusRequester2),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.None
                    ),
                    placeholder = { Text(stringResource(R.string._0000)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequester3.requestFocus()
                        }
                    ),
                )
                TextField(
                    value = serverUser,
                    onValueChange = { globalSettings.serverAddress.serverUser = it },
                    label = { Text(stringResource(R.string.server_user)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 6.dp, 20.dp, 20.dp)
                        .border(
                            dimensionResource(R.dimen.textfield_border_size),
                            MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .focusRequester(focusRequester3),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.None
                    ),
                    placeholder = { Text(stringResource(R.string.enter_server_user)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusRequester4.requestFocus()
                        }
                    ),
                )
                TextField(
                    value = serverPassword,
                    onValueChange = { globalSettings.serverAddress.serverPassword = it },
                    label = { Text(stringResource(R.string.server_password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 6.dp, 20.dp, 20.dp)
                        .border(
                            dimensionResource(R.dimen.textfield_border_size),
                            MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .focusRequester(focusRequester4),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.None
                    ),
                    placeholder = { Text(stringResource(R.string.enter_server_password)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                )
            }
            SettingsSwitchRow(
                title = stringResource(R.string.analytics_enabled),
                isChecked = globalSettings.analyticsEnabled,
                onToggle = { onToggleAnalyticsEnabled(it) }
            )
            SettingsSwitchRow(
                title = stringResource(R.string.chat_enabled),
                isChecked = globalSettings.chatEnabled,
                onToggle = { onToggleChatEnabled(it) }
            )
            SettingsSwitchRow(
                title = stringResource(R.string.dark_mode),
                isChecked = globalSettings.darkMode,
                onToggle = { onToggleDarkMode(it) }
            )
        }
    }

}