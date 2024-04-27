package com.example.budgetwise.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.budgetwise.database.dao.CategoryDao
import com.example.budgetwise.database.entities.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 1
)
abstract class BudgetwiseDatabase : RoomDatabase(){
    abstract fun getCategorieDao():CategoryDao
}