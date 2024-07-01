package com.jsoftware.recipes.domain.repository

import com.jsoftware.recipes.domain.models.CategoryList
import com.jsoftware.recipes.domain.models.Meal
import com.jsoftware.recipes.domain.models.MealCategory
import com.jsoftware.recipes.domain.models.MealList
import com.jsoftware.recipes.domain.models.PopularMeal

interface MealRepository {
    suspend fun getRandomMeal(): Meal

    suspend fun getMealCategories(): CategoryList

    suspend fun getMealById(id: String): Meal

    suspend fun getPopularMeal(): PopularMeal

    suspend fun getMealsByCategory(category: String):PopularMeal
}