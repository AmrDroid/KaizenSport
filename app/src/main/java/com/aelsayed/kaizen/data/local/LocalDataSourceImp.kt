package com.aelsayed.kaizen.data.local

import com.aelsayed.kaizen.data.local.entity.CategoryEntity
import com.aelsayed.kaizen.data.local.entity.MatchEventEntity


class LocalDataSourceImp(private val appDatabase: AppDatabase) : LocalDataSource {

    override suspend fun insertSportCategory(categoryEntity: CategoryEntity) {
        appDatabase.categoryDao.insertSportCategory(categoryEntity)
    }

    override suspend fun deleteSportCategoryEntity(id: String) {
        appDatabase.categoryDao.deleteSportCategoryEntity(id)
    }

    override suspend fun deleteAllCategories() {
        appDatabase.categoryDao.deleteAllCategories()
    }

    override suspend fun getAllCategories(): List<CategoryEntity> {
        return appDatabase.categoryDao.getAllCategories()
    }

    override suspend fun addMatchEvent(matchEventEntity: MatchEventEntity) {
        appDatabase.matchEventDao.addMatchEvent(matchEventEntity)
    }

    override suspend fun deleteMatchEvent(eventId: String) {
        appDatabase.matchEventDao.deleteMatchEvent(eventId)
    }

    override suspend fun updateMatchEvent(matchEventEntity: MatchEventEntity) {
        appDatabase.matchEventDao.updateMatchEvent(matchEventEntity)
    }

    override suspend fun deleteAllMatchEvents() {
        appDatabase.matchEventDao.deleteAllMatchEvents()
    }

    override suspend fun getAllMatchEvents(): List<MatchEventEntity> {
        return appDatabase.matchEventDao.getAllMatchEvents()
    }
}