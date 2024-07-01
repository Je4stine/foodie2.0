package com.jsoftware.recipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jsoftware.recipes.domain.models.MealList

@Database(
    entities = [MealList::class],
    version = 1
)

@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}