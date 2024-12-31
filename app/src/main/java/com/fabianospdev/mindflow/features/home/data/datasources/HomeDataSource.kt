package com.fabianospdev.mindflow.features.home.data.datasources

import com.fabianospdev.mindflow.features.home.data.models.HomeRequestModel
import com.fabianospdev.mindflow.features.home.data.models.HomeResponseModel


interface HomeDataSource {
    suspend fun getHomeContent(request: HomeRequestModel): Result<HomeResponseModel>
}