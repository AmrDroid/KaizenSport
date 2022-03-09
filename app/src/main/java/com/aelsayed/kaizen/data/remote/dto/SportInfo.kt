package com.aelsayed.kaizen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SportInfo(
    @SerializedName("d")
    val category: String,
    @SerializedName("e")
    val events: List<MatchEvent>,
    @SerializedName("i")
    val sportInfoId: String
)