package com.fabianospdev.mindflow.features.home.data.repositories

import com.fabianospdev.mindflow.features.home.data.datasources.HomeDataSource
import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity
import com.fabianospdev.mindflow.features.home.domain.repositories.HomeRemoteRepository

class HomeRemoteRepositoryImpl(private val loginDataSource: HomeDataSource) : HomeRemoteRepository {
    override suspend fun getHomeContent(): Result<HomeResponseEntity> {
        TODO("Not yet implemented")
    }
}