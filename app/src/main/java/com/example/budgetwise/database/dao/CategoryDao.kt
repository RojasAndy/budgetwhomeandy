package com.example.budgetwise.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.budgetwise.database.entities.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categorye: CategoryEntity)

    @Insert
    suspend fun insertMany(categorym: List<CategoryEntity>)

    @Query("SELECT COUNT(*) FROM category_table")
    suspend fun getCount(): Int

    @Query("SELECT * FROM category_table")
    suspend fun getAllCats() : List<CategoryEntity>

    @Query("UPDATE category_table SET progress= :x WHERE id = :idx")
    suspend fun updateById(idx: Int, x: Int)

    @Query("SELECT progress FROM category_table WHERE id = :idx")
    suspend fun getProgress(idx: Int): Int?

    @Query("SELECT SUM(progress) FROM category_table")
    suspend fun sumProgress(): Int

    @Query("SELECT SUM(max_progress) FROM category_table")
    suspend fun sumMax(): Int
}