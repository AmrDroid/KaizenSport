package com.aelsayed.kaizen.data.local

import androidx.room.Query
import com.aelsayed.kaizen.data.local.entity.CategoryEntity
import com.aelsayed.kaizen.data.local.entity.MatchEventEntity


interface LocalDataSource {

    suspend fun insertSportCategory(categoryEntity: CategoryEntity)

    suspend fun deleteSportCategoryEntity(id: String)

    suspend fun deleteAllCategories()

    suspend fun getAllCategories(): List<CategoryEntity>

    suspend fun addMatchEvent(matchEventEntity: MatchEventEntity)

    suspend fun deleteMatchEvent(eventId: String)

    suspend fun updateMatchEvent(matchEventEntity: MatchEventEntity)

    suspend fun deleteAllMatchEvents()

    suspend fun getAllMatchEvents(): List<MatchEventEntity>


}