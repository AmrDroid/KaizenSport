package com.aelsayed.kaizen.data.remote

import com.aelsayed.kaizen.data.remote.dto.SportInfo


interface RemoteDataSource {
    suspend fun getMatchesInfo(): List<SportInfo>?

}