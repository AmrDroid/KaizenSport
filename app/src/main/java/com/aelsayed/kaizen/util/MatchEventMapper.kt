package com.aelsayed.kaizen.util

import com.aelsayed.kaizen.data.local.entity.MatchEventEntity
import com.aelsayed.kaizen.data.remote.dto.MatchEventResponse
import com.aelsayed.kaizen.domain.model.MatchEvent

object MatchEventMapper {

    fun dtoToEntity(matchEventResponse: MatchEventResponse): MatchEventEntity{
        return MatchEventEntity(
            eventId = matchEventResponse.eventId,
            eventName = matchEventResponse.eventName,
            eventStartTime = matchEventResponse.eventStartTime,
            sportId = matchEventResponse.sportId
        )
    }

    fun entityToModel(matchEventEntity: MatchEventEntity): MatchEvent {
        return MatchEvent(
            eventId = matchEventEntity.eventId,
            eventName = matchEventEntity.eventName,
            eventStartTime = matchEventEntity.eventStartTime,
            sportId = matchEventEntity.sportId
        )
    }
}