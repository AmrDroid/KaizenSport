package com.aelsayed.kaizen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEventEntity(
    @PrimaryKey
    val eventId: String,
    val eventName: String,
    val eventStartTime: String,
    val isEventFavourite: Boolean = false,
    val sportId: String
)
