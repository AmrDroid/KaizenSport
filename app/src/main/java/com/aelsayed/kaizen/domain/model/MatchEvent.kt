package com.aelsayed.kaizen.domain.model

data class MatchEvent(
    val eventId: String,
    val eventName: String,
    var eventStartTime: String,
    var isEventFavourite: Boolean = false,
    val sportId: String,
    val eventTimeFormatted : String = "-"
)