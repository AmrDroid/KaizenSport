package com.aelsayed.kaizen.di

import com.aelsayed.kaizen.presentation.viewmodel.KaizenSportViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        KaizenSportViewModel(
            get(named(PARAMS.GetCategoryUseCase)),
            get(named(PARAMS.GetMatchesUseCase)),
            get(named(PARAMS.AddMatchFavourite))
        )
    }
}