package com.fabianospdev.mindflow.features.home.data.datasources

import com.fabianospdev.mindflow.features.home.data.models.HomeResponseModel
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val retrofitService: HomeDataSource
) : HomeDataSource {

    override suspend fun getHomeContent(): HomeResponseModel {
        try {

            return retrofitService.getHomeContent()
        } catch (e: Exception) {
            throw Throwable("Authentication error: ${e.message}", e)
        }
    }
}