package com.fabianospdev.mindflow.features.login.presentation.ui.login.components

import androidx.compose.runtime.Composable
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError

@Composable
fun ShowCommonError() {
    CommonError.UnknownError.message
}