package com.aelsayed.kaizen.data.remote

import com.aelsayed.kaizen.data.remote.dto.SportInfoResponse
import retrofit2.http.GET

interface ApiService {

    @GET("api/sports")
    suspend fun getMatchesInfo(): List<SportInfoResponse>?

}