package com.aelsayed.kaizen.domain.usecases

import com.aelsayed.kaizen.domain.model.Category
import com.aelsayed.kaizen.domain.repository.ISportRepository
import com.aelsayed.kaizen.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategories  constructor(
    private val repositoryI: ISportRepository
) : MainUseCase<String, Flow<Resource<List<Category>>>> {

    override fun invoke(params: String) = flow {

        emit(Resource.Loading())

        val categories = repositoryI.getCategories()

        if (categories.isNotEmpty())
            emit(Resource.Success(categories))
        else
            emit(Resource.Error(message = "An error occurred check your internet connection."))

    }
}