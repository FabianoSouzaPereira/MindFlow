package com.fabianospdev.mindflow.features.home.data.datasources

import com.fabianospdev.mindflow.features.home.data.models.HomeResponseModel
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val retrofitService: HomeDataSource
) : HomeDataSource {
    override suspend fun getHomeContent(): HomeResponseModel {
        return retrofitService.getHomeContent()
    }
}