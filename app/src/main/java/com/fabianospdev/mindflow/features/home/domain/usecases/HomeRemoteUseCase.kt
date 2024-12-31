package com.fabianospdev.mindflow.features.home.domain.usecases

import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity

interface HomeRemoteUseCase {
    suspend fun getHomeContent() : Result<HomeResponseEntity>
}