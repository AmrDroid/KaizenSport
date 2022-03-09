package com.aelsayed.kaizen.domain.usecases

import com.aelsayed.kaizen.domain.model.MatchEvent

class UpdateMatchFavourite : MainUseCase<Pair<List<MatchEvent>, MatchEvent>, List<MatchEvent>> {

    override fun invoke(params: Pair<List<MatchEvent>, MatchEvent>): List<MatchEvent> {
        val mutableMatches = params.first.toMutableList()
        mutableMatches.remove(params.second)
        mutableMatches.add(params.second.copy(isEventFavourite = !params.second.isEventFavourite))
        return mutableMatches.toList()
    }


}