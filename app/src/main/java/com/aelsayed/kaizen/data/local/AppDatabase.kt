package com.aelsayed.kaizen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aelsayed.kaizen.data.local.dao.CategoryDao
import com.aelsayed.kaizen.data.local.dao.MatchEventDao
import com.aelsayed.kaizen.data.local.entity.CategoryEntity
import com.aelsayed.kaizen.data.local.entity.MatchEventEntity

@Database(
    entities = [(CategoryEntity::class), (MatchEventEntity::class)],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val matchEventDao: MatchEventDao
}