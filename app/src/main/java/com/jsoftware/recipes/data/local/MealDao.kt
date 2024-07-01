package com.jsoftware.recipes.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jsoftware.recipes.domain.models.Meal
import com.jsoftware.recipes.domain.models.MealList

@Dao
interface MealDao {

    @Upsert
    suspend fun insertMeal(meal: MealList)

    @Query("SELECT * FROM MEALLIST")
    suspend fun getAll (): List<MealList>
}