package com.example.budgetwise.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "progress") val progress: Int,
    @ColumnInfo(name = "max_progress") val maxProgress: Int,
    @ColumnInfo(name = "icon") val icLogoResource: Int,
    @ColumnInfo(name = "color_resource") val colorResource: Int
)