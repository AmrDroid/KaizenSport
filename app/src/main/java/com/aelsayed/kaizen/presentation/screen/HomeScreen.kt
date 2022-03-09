package com.aelsayed.kaizen.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aelsayed.kaizen.R
import com.aelsayed.kaizen.presentation.components.CategoryBar
import com.aelsayed.kaizen.presentation.components.CustomTopAppBar
import com.aelsayed.kaizen.presentation.components.MatchCard
import com.aelsayed.kaizen.presentation.viewmodel.KaizenSportViewModel

@Composable
fun HomeScreen(viewModel: KaizenSportViewModel) {


    Scaffold(topBar = {
        CustomTopAppBar(title = stringResource(R.string.app_name))
    }) {

        val state by viewModel.state.collectAsState()

        if (state.isLoading)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        else
            LazyColumn(contentPadding = PaddingValues(vertical = 24.dp)) {


                items(viewModel.state.value.categoryList) { category ->

                    var isExpanded = true
                    when (category.id) {

                        stringResource(R.string.foot) -> {
                            isExpanded = state.isSoccerExpanded
                        }

                        stringResource(R.string.bask) -> {
                            isExpanded = state.isBasketballExpanded
                        }

                        stringResource(R.string.tenn) -> {
                            isExpanded = state.isTennisExpanded
                        }

                        stringResource(R.string.tabl) -> {
                            isExpanded = state.isTableTennisExpanded
                        }

                        stringResource(R.string.voll) -> {
                            isExpanded = state.isVolleyballExpanded
                        }

                        stringResource(R.string.esps) -> {
                            isExpanded = state.isEsportsExpanded
                        }

                        stringResource(R.string.iceh) -> {
                            isExpanded = state.isIceHockeyExpanded
                        }

                        stringResource(R.string.hand) -> {
                            isExpanded = state.isHandballExpanded
                        }

                        stringResource(R.string.bchv) -> {
                            isExpanded = state.isBeachVolleyExpanded
                        }

                        stringResource(R.string.snoo) -> {
                            isExpanded = state.isSnookerExpanded
                        }

                        stringResource(R.string.badm) -> {
                            isExpanded = state.isBadmintonExpanded
                        }

                    }

                    CategoryBar(category = category, isExpanded = isExpanded) {
                        viewModel.updateExpandCategory(category)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    if (isExpanded) {
                        LazyRow() {
                            items(viewModel.getMatchesOfCategory(category)) { matchEvent ->
                                MatchCard(
                                    matchEvent = matchEvent,
                                    updateFavouriteMatch = {
                                        viewModel.updateFavouriteMatch(
                                            matchEvent
                                        )
                                    },
                                    updateCountDown = { viewModel.updateCountDown(it, matchEvent) })
                                Spacer(modifier = Modifier.width(24.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                }
            }


    }

}