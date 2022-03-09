package com.aelsayed.kaizen.data.repository

import android.util.Log
import com.aelsayed.kaizen.data.local.LocalDataSource
import com.aelsayed.kaizen.data.remote.RemoteDataSource
import com.aelsayed.kaizen.domain.model.Category
import com.aelsayed.kaizen.domain.model.MatchEvent
import com.aelsayed.kaizen.domain.repository.ISportRepository
import com.aelsayed.kaizen.util.CategoryMapper
import com.aelsayed.kaizen.util.MatchEventMapper
import java.lang.Exception

class ISportRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ISportRepository {


    override suspend fun getCategories(): List<Category> {
        try {

            val categories = localDataSource.getAllCategories()
                .map { CategoryMapper.entityToModel(it) } //Checking if the data are in room database

            if (categories.isNullOrEmpty()) { //Data are not in room database

                val response = remoteDataSource.getMatchesInfo() //Getting data from api
                if (response.isNullOrEmpty()) {
                    throw Exception("Error fetching data from API.")
                } else {

                    localDataSource.deleteAllCategories() //Cleaning room database
                    response.forEach {
                        val category = CategoryMapper.dtoToEntity(it)
                        localDataSource.insertSportCategory(category) //Adding each category to room database
                    }

                    return localDataSource.getAllCategories()
                        .map { CategoryMapper.entityToModel(it) } //Getting categories from database


                }

            } else { //Data not in room database
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
                        val match = it.eventResponses

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