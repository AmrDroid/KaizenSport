package com.aelsayed.kaizen.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.aelsayed.kaizen.R
import com.aelsayed.kaizen.presentation.components.CategoryBar
import com.aelsayed.kaizen.presentation.components.CustomTopAppBar
import com.aelsayed.kaizen.presentation.components.MatchCard
import com.aelsayed.kaizen.presentation.viewmodel.KaizenSportState
import com.aelsayed.kaizen.presentation.viewmodel.KaizenSportViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


@Composable
fun <T> rememberFlow(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
): Flow<T> {
    return remember(key1 = flow, key2 = lifecycleOwner) { flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED) }
}

@Composable
fun <T : R, R> Flow<T>.collectAsStateLifecycleAware(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext
): State<R> {
    val lifecycleAwareFlow = rememberFlow(flow = this)
    return lifecycleAwareFlow.collectAsState(initial = initial, context = context)
}

@Suppress("StateFlowValueCalledInComposition")
@Composable
fun <T> StateFlow<T>.collectAsStateLifecycleAware(
    context: CoroutineContext = EmptyCoroutineContext
): State<T> = collectAsStateLifecycleAware(value, context)

@Composable
fun HomeScreen(viewModel: KaizenSportViewModel) {


    Scaffold(topBar = {
        CustomTopAppBar(title = stringResource(R.string.app_name))
    }) {

        //        val state by viewModel.state.collectAsState()

        val state: KaizenSportState by viewModel.state.collectAsStateLifecycleAware()


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