package com.aelsayed.kaizen.presentation.viewmodel

import android.util.Log
import com.aelsayed.kaizen.domain.model.Category
import com.aelsayed.kaizen.domain.model.MatchEvent
import com.aelsayed.kaizen.domain.usecases.*
import com.aelsayed.kaizen.util.Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class KaizenSportViewModel constructor(
    private val getCategories: MainUseCase<String, Flow<Resource<List<Category>>>>,
    private val getMatches: MainUseCase<String, Flow<Resource<List<MatchEvent>>>>,
    private val updateMatchFavourite: MainUseCase<Pair<List<MatchEvent>, MatchEvent>, List<MatchEvent>>
) : ViewModel() {

    private val _state = MutableStateFlow(KaizenSportState())
    val state: StateFlow<KaizenSportState> = _state

    init {
        initialCategories()
        initialMatches()
    }

    private fun initialCategories() {

        viewModelScope.launch(Dispatchers.IO) {

            getCategories.invoke("").collect {

                when (it) {

                    is Resource.Loading -> {
                        _state.value = KaizenSportState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _state.value.isLoading=false
                        _state.value = KaizenSportState(
                            message = it.message ?: "An unexpected error occurred."
                        )
                    }

                    is Resource.Success -> {
                        _state.value.isLoading=false
                        _state.value = _state.value.copy(categoryList = it.data ?: emptyList())
                        Log.d("AMR MMM",_state.value.toString())
                    }

                }

            }
        }

    }

    private fun initialMatches() {

        viewModelScope.launch(Dispatchers.IO) {
            getMatches.invoke("").collect { result ->
                when (result) {

                    is Resource.Loading -> {
                        _state.value = KaizenSportState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _state.value.isLoading=false
                        _state.value = KaizenSportState(
                            message = result.message ?: "An unexpected error occurred."
                        )
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(matchesList = result.data ?: emptyList())
                        Log.d("AMR MMM",_state.value.toString())
                    }

                }
            }
        }
    }

    fun getMatchesOfCategory(category: Category): List<MatchEvent> {

        return _state.value.matchesList.filter { it.sportId == category.id }
            .sortedByDescending { it.isEventFavourite }


    }

    fun updateFavouriteMatch(matchEvent: MatchEvent) {

        val updatedMatches = updateMatchFavourite(Pair(_state.value.matchesList, matchEvent))

        _state.value = state.value.copy(
            matchesList = updatedMatches.sortedByDescending { it.isEventFavourite },
            message = "${matchEvent.eventName} added to Favourite"
        )

    }

    fun updateExpandCategory(category: Category) {

        when (category.id) {

            "FOOT" -> {
                _state.value = _state.value.copy(isSoccerExpanded = !_state.value.isSoccerExpanded)
            }

            "BASK" -> {
                _state.value =
                    _state.value.copy(isBasketballExpanded = !_state.value.isBasketballExpanded)
            }

            "TENN" -> {
                _state.value = _state.value.copy(isTennisExpanded = !_state.value.isTennisExpanded)
            }

            "TABL" -> {
                _state.value =
                    _state.value.copy(isTableTennisExpanded = !_state.value.isTableTennisExpanded)
            }

            "VOLL" -> {
                _state.value =
                    _state.value.copy(isVolleyballExpanded = !_state.value.isVolleyballExpanded)
            }

            "ESPS" -> {
                _state.value =
                    _state.value.copy(isEsportsExpanded = !_state.value.isEsportsExpanded)
            }

            "ICEH" -> {
                _state.value =
                    _state.value.copy(isIceHockeyExpanded = !_state.value.isIceHockeyExpanded)
            }

            "HAND" -> {
                _state.value =
                    _state.value.copy(isHandballExpanded = !_state.value.isHandballExpanded)
            }

            "BCHV" -> {
                _state.value =
                    _state.value.copy(isBeachVolleyExpanded = !_state.value.isBeachVolleyExpanded)
            }

            "SNOO" -> {
                _state.value =
                    _state.value.copy(isSnookerExpanded = !_state.value.isSnookerExpanded)
            }

            "BADM" -> {
                _state.value =
                    _state.value.copy(isBadmintonExpanded = !_state.value.isBadmintonExpanded)
            }

        }

    }

    fun updateCountDown(newTime: String, matchEvent: MatchEvent) {

        val matches = _state.value.matchesList
        matches.forEach {
            if (it.eventId == matchEvent.eventId) {
                it.eventStartTime = newTime
            }
        }
    }


}