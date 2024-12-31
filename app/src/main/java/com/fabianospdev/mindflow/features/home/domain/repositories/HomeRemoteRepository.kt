package com.fabianospdev.mindflow.features.home.domain.repositories

import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity

interface HomeRemoteRepository {
    suspend fun getHomeContent() : Result<HomeResponseEntity>
}