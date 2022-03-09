package com.aelsayed.kaizen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SportInfoResponse(
    @SerializedName("d")
    val category: String,
    @SerializedName("e")
    val eventResponses: List<MatchEventResponse>,
    @SerializedName("i")
    val sportInfoId: String
)