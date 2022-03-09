package com.aelsayed.kaizen.data.repository

import android.util.Log
import com.aelsayed.kaizen.data.local.LocalDataSource
import com.aelsayed.kaizen.data.remote.RemoteDataSource
import com.aelsayed.kaizen.domain.model.Category
import com.aelsayed.kaizen.domain.model.MatchEvent
import com.aelsayed.kaizen.domain.repository.ISportRepository
import com.aelsayed.kaizen.presentation.CategoryMapper
import com.aelsayed.kaizen.util.mappers.MatchEventMapper
import java.lang.Exception

class ISportRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ISportRepository {


    override suspend fun getCategories(): List<Category> {

        try {
            val categories = localDataSource.getAllCategories()
                .map { CategoryMapper.entityToModel(it) }

            if (categories.isNullOrEmpty()) {

                val response = remoteDataSource.getMatchesInfo()
                if (response.isNullOrEmpty()) {
                    throw Exception("Error fetching data from API.")
                } else {
                    localDataSource.deleteAllCategories()
                    response.forEach {
                        val category = CategoryMapper.dtoToEntity(it)
                        localDataSource.insertSportCategory(category)
                    }

                    return localDataSource.getAllCategories()
                        .map { CategoryMapper.entityToModel(it) }
                }
            } else {
                return categories
            }

        } catch (e: Exception) {
            Log.e("RepoImpl", "getCategories: ${e.message}")
            return emptyList()
        }
    }

    override suspend fun getMatchEvents(): List<MatchEvent> {
        try {
            val events = localDataSource.getAllMatchEvents()
                .map { MatchEventMapper.entityToModel(it) }

            if (events.isNullOrEmpty()) {

                val response = remoteDataSource.getMatchesInfo()

                if (response.isNullOrEmpty()) {

                    throw Exception("Error fetching data from API.")

                } else {
                    //For every category, add all matches to our database
                    localDataSource.deleteAllMatchEvents()
                    response.forEach {
                        val match = it.events

                        match.forEach { m ->
                            localDataSource.addMatchEvent(MatchEventMapper.dtoToEntity(m))
                        }
                    }
                    return localDataSource.getAllMatchEvents()
                        .map { MatchEventMapper.entityToModel(it) }
                }

            } else
                return events

        } catch (e: Exception) {
            Log.e("RepoImpl", "getMatchEvents: ${e.message}")
            return emptyList()
        }
    }

    override suspend fun clearDatabase() {
        localDataSource.deleteAllCategories()
        localDataSource.deleteAllMatchEvents()
    }


}