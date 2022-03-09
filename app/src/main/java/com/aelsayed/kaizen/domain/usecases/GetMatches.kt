package com.aelsayed.kaizen.domain.usecases

import com.aelsayed.kaizen.domain.model.MatchEvent
import com.aelsayed.kaizen.domain.repository.ISportRepository
import com.aelsayed.kaizen.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetMatches constructor(
    private val repositoryI: ISportRepository
) : MainUseCase<String, Flow<Resource<List<MatchEvent>>>> {

    override fun invoke(params: String) = flow {
        emit(Resource.Loading())

        val matches = repositoryI.getMatchEvents()

        if (matches.isNotEmpty())
            emit(Resource.Success(matches))
        else
            emit(Resource.Error(message = "An error occurred check your internet connection."))

    }
}