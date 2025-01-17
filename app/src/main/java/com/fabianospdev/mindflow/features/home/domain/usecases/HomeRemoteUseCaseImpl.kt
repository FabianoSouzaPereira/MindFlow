package com.fabianospdev.mindflow.features.home.domain.usecases

import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity
import com.fabianospdev.mindflow.features.home.domain.repositories.HomeRemoteRepository
import javax.inject.Inject

class HomeRemoteUseCaseImpl @Inject constructor(
    private val repository: HomeRemoteRepository
) : HomeRemoteUseCase {

    override suspend fun getHomeContent(): Result<HomeResponseEntity> {
        return repository.getHomeContent()
    }
}