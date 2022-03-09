package com.aelsayed.kaizen.data.remote

import com.aelsayed.kaizen.data.remote.dto.SportInfoResponse

class RemoteDataSourceImp(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getMatchesInfo(): List<SportInfoResponse>? {
       return apiService.getMatchesInfo()
    }
}