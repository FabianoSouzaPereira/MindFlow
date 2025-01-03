package com.fabianospdev.mindflow.features.login.presentation.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.core.utils.LoadFontsFamily
import com.fabianospdev.mindflow.features.login.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController,
    name: String
) {
    val state by viewModel.state.observeAsState(LoginState.Idle)
    val context = LocalContext.current

    /** Observing the ViewModel state **/
    val isUsingFirebase by viewModel.isUsingFirebase.collectAsState()
    val username by remember { viewModel.username }
    val password by remember { viewModel.password }

    /** Field validation (calculated in a derived way) **/
    val isUserNameEmpty by remember { viewModel.isUserNameEmpty }
    val isPasswordEmpty by remember { viewModel.isPasswordEmpty }
    val isFormValid by remember { viewModel.isFormValid }

    val showPassword = remember { mutableStateOf(value = false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val showRetryLimitReached by viewModel.showRetryLimitReached.collectAsState()
    var showPopup by remember { mutableStateOf(false) }

    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    )

    if (showRetryLimitReached && !showPopup) {
        showPopup = true
        viewModel.resetRetryLimitNotification()
    }

    if (showPopup) {
        ShowPopup(
            viewModel = viewModel,
            message = stringResource(R.string.attempt_limit_reached),
            onDismiss = { showPopup = false },
            imageResId = R.mipmap.ic_launcher_foreground
        )
        viewModel.resetState()
    }

    when (state) {
        is LoginState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoginState.Idle -> {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.onSurface,
                tonalElevation = 5.dp
            ){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradient)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .size(300.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.login),
                            fontSize = with(LocalDensity.current) {
                                dimensionResource(id = R.dimen.title_font_text_size).value.sp
                            },
                            fontFamily = LoadFontsFamily.karlaFamily,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontStyle = FontStyle.Normal
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.minium_space_betwing_elements)))
                        TextField(
                            value = username,
                            onValueChange = {
                                viewModel.username.value = it
                            },
                            label = { Text(stringResource(R.string.email)) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusRequester.requestFocus()
                                }
                            ),
                            textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                            shape = RoundedCornerShape(25),
                            modifier = Modifier
                                .padding(20.dp, 20.dp, 20.dp, 1.dp)
                                .border(
                                    dimensionResource(R.dimen.textfield_border_size),
                                    MaterialTheme.colorScheme.onPrimary,
                                    shape = RoundedCornerShape(dimensionResource(R.dimen.textfield_rounded_corner_shape))
                                )
                                .clip(RoundedCornerShape(dimensionResource(R.dimen.textfield_rounded_corner_shape)))
                                .focusRequester(focusRequester),
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.email_email_com),
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Normal
                                    ),
                                    maxLines = 1
                                )
                            },
                            maxLines = 1
                        )
                        TextField(
                            value = password,
                            onValueChange = {
                                viewModel.password.value = it
                            },
                            label = { Text(stringResource(R.string.password)) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            visualTransformation = if (showPassword.value) VisualTransformation.None else
                                PasswordVisualTransformation(),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.None
                            ),
                            shape = RoundedCornerShape(25),
                            modifier = Modifier
                                .padding(20.dp, 6.dp, 20.dp, 20.dp)
                                .border(
                                    dimensionResource(R.dimen.textfield_border_size),
                                    MaterialTheme.colorScheme.onPrimary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clip(RoundedCornerShape(16.dp))
                                .focusRequester(focusRequester),
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.q_nm_44u),
                                    fontSize = 16.sp,
                                    fontFamily = LoadFontsFamily.montserratFamily,
                                    fontWeight = FontWeight.Normal,
                                    style = LocalTextStyle.current.copy(
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                                    ),
                                    maxLines = 1
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = stringResource(R.string.password_icon),
                                    tint = if (isPasswordEmpty) Color.Gray else Color.Green
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                                    val icon: ImageVector = if (showPassword.value) {
                                        ImageVector.vectorResource(id = R.drawable.baseline_remove_red_eye_24)
                                    } else {
                                        ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24)
                                    }
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = if (showPassword.value) stringResource(id = R.string.hide_password) else
                                            stringResource(id = R.string.show_password),
                                        tint = if (showPassword.value) Color.Blue else Color.Gray
                                    )
                                }
                            },
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = {
                                viewModel.login(username, password)
                            },
                            enabled = isFormValid,
                            interactionSource = remember { MutableInteractionSource() },
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                            shape = MaterialTheme.shapes.large,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier
                                .width(dimensionResource(R.dimen.button_width_medium))
                                .padding(dimensionResource(R.dimen.button_padding))
                                .clip(RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape)))
                                .background(
                                    brush = if (!isFormValid) Brush.verticalGradient(
                                        listOf(
                                            MaterialTheme.colorScheme.surfaceVariant,
                                            MaterialTheme.colorScheme.surfaceVariant)) else gradient)
                                .border(
                                    BorderStroke(
                                        width = dimensionResource(R.dimen.button_border_size),
                                        color = if(!isFormValid) MaterialTheme.colorScheme.outlineVariant else
                                            MaterialTheme.colorScheme.onPrimary
                                    ),
                                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape))
                                ),
                            contentPadding = ButtonDefaults.ContentPadding
                        ) {
                            Text(
                                text = context.getString(R.string.login),
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                color = if(!isFormValid) MaterialTheme.colorScheme.outline else
                                    MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        }
        is LoginState.Success -> {}
        is LoginState.Error -> {}
        is LoginState.NoConnection -> {}
        is LoginState.TimeoutError -> {}
        is LoginState.Unauthorized -> {}
        is LoginState.ValidationError -> {}

    }
}

@Composable
fun ShowPopup(
    viewModel: LoginViewModel,
    message: String,
    onDismiss: () -> Unit,
    imageResId: Int
) {
    Popup(
        alignment = Alignment.Center,
        onDismissRequest = onDismiss
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            modifier = Modifier
                .padding(32.dp)
                .width(320.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(24.dp)
                    .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Popup Image",
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = message,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        color =  MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LaunchedEffect(Unit) {
                    delay(3000)
                    viewModel.clearInputFields()
                    onDismiss()
                }
            }
        }
    }
}
