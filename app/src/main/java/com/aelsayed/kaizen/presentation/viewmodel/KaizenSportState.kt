package com.aelsayed.kaizen.presentation.viewmodel

import com.aelsayed.kaizen.domain.model.Category
import com.aelsayed.kaizen.domain.model.MatchEvent

data class KaizenSportState(
    val categoryList: List<Category> = emptyList(),
    val matchesList: List<MatchEvent> = emptyList(),
    var isLoading: Boolean = false,
    val message: String = "",
    val isSoccerExpanded: Boolean = true,
    val isBasketballExpanded: Boolean = true,
    val isTennisExpanded: Boolean = true,
    val isTableTennisExpanded: Boolean = true,
    val isVolleyballExpanded: Boolean = true,
    val isEsportsExpanded: Boolean = true,
    val isIceHockeyExpanded: Boolean = true,
    val isHandballExpanded: Boolean = true,
    val isBeachVolleyExpanded: Boolean = true,
    val isSnookerExpanded: Boolean = true,
    val isBadmintonExpanded: Boolean = true,

)
