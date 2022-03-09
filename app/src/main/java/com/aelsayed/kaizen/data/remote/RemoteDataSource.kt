package com.aelsayed.kaizen.data.remote

import com.aelsayed.kaizen.data.remote.dto.SportInfoResponse


interface RemoteDataSource {
    suspend fun getMatchesInfo(): List<SportInfoResponse>?

}