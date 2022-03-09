package com.aelsayed.kaizen.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aelsayed.kaizen.data.local.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSportCategory(categoryEntity: CategoryEntity)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun deleteSportCategoryEntity(id: String)

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryEntity>

}