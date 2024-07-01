package com.jsoftware.recipes.domain.repository

import com.jsoftware.recipes.domain.models.Meal
import com.jsoftware.recipes.domain.models.MealList

interface MealDbRepo {
    suspend fun insertMeal(meal: MealList)

    suspend fun getAll (): List<MealList>
}