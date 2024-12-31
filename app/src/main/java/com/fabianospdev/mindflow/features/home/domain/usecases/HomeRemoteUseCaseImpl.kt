package com.fabianospdev.mindflow.features.home.domain.usecases

import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity
import com.fabianospdev.mindflow.features.home.domain.repositories.HomeRemoteRepository

class HomeRemoteUseCaseImpl(private val repository: HomeRemoteRepository) : HomeRemoteUseCase {
    override suspend fun getHomeContent(): Result<HomeResponseEntity> {
        TODO("Not yet implemented")
    }
}