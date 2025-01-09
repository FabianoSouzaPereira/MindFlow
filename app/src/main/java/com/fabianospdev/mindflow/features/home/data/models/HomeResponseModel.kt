package com.fabianospdev.mindflow.features.home.data.models

import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity
import com.fabianospdev.mindflow.features.home.domain.entities.Mood

class HomeResponseModel(override val id: String, override val mood: Mood) : HomeResponseEntity