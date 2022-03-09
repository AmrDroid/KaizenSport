package com.aelsayed.kaizen.data.remote

import com.aelsayed.kaizen.data.remote.dto.SportInfo

class RemoteDataSourceImp(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getMatchesInfo(): List<SportInfo>? {
       return apiService.getMatchesInfo()
    }
}