package com.fabianospdev.mindflow.features.home.data.datasources

import com.fabianospdev.mindflow.features.home.data.models.HomeRequestModel
import com.fabianospdev.mindflow.features.home.data.models.HomeResponseModel
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val retrofitService: HomeDataSource
) : HomeDataSource {
    override suspend fun getHomeContent(request: HomeRequestModel): HomeResponseModel {
        return retrofitService.getHomeContent(request)
    }
}