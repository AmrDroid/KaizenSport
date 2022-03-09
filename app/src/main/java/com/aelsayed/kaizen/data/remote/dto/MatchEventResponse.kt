package com.aelsayed.kaizen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MatchEventResponse(

    @SerializedName("d")
    val eventName: String,
    @SerializedName("i")
    val eventId: String,
    val sh: String,
    @SerializedName("si")
    val sportId: String,
    @SerializedName("tt")
    val eventStartTime: String
)