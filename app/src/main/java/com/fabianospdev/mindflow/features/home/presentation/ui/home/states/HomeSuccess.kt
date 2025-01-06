package com.fabianospdev.mindflow.features.home.presentation.ui.home.states

import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity

data class HomeSuccess(val response: HomeResponseEntity) : HomeState()