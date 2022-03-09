package com.aelsayed.kaizen.domain.repository

import com.aelsayed.kaizen.domain.model.Category
import com.aelsayed.kaizen.domain.model.MatchEvent
import com.aelsayed.kaizen.util.Resource
import kotlinx.coroutines.flow.Flow

interface ISportRepository {

    suspend fun getCategories(): List<Category>

    suspend fun getMatchEvents(): List<MatchEvent>

    suspend fun clearDatabase()


}