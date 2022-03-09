package com.aelsayed.kaizen.di

import com.aelsayed.kaizen.domain.model.Category
import com.aelsayed.kaizen.domain.model.MatchEvent
import com.aelsayed.kaizen.domain.repository.ISportRepository
import com.aelsayed.kaizen.domain.usecases.*
import com.aelsayed.kaizen.util.Resource
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module
import org.koin.core.qualifier.named

val useCasesModule = module {

    single(named(PARAMS.GetCategoryUseCase)) { provideGetCategoryUseCase(get()) }
    single(named(PARAMS.GetMatchesUseCase)) { provideGetMatchesUseCase(get()) }
    single(named(PARAMS.AddMatchFavourite)) { provideAddMatchFavourite() }

}

fun provideGetCategoryUseCase(sportRepository: ISportRepository): MainUseCase<String, Flow<Resource<List<Category>>>> {
    return GetCategories(sportRepository)
}


fun provideGetMatchesUseCase(sportRepository: ISportRepository): MainUseCase<String, Flow<Resource<List<MatchEvent>>>> {
    return GetMatches(sportRepository)
}


fun provideAddMatchFavourite(): MainUseCase<Pair<List<MatchEvent>, MatchEvent>, List<MatchEvent>> {
    return UpdateMatchFavourite()
}


object PARAMS {
    const val GetCategoryUseCase = "GetCategoryUseCase"
    const val GetMatchesUseCase = "GetMatchesUseCase"
    const val AddMatchFavourite = "AddMatchFavourite"
}



