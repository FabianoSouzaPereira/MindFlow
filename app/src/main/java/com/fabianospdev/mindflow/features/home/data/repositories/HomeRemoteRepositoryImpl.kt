package com.fabianospdev.mindflow.features.home.data.repositories

import android.content.Context
import com.fabianospdev.mindflow.features.home.data.datasources.HomeDataSource
import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity
import com.fabianospdev.mindflow.features.home.domain.repositories.HomeRemoteRepository

class HomeRemoteRepositoryImpl(
    private val homeDataSource: HomeDataSource,
    private val context: Context
) : HomeRemoteRepository {
    override suspend fun getHomeContent(): Result<HomeResponseEntity> {
        return try {
            val response = homeDataSource.getHomeContent()
            return Result.success(response as HomeResponseEntity)

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}
