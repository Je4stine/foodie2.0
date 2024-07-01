package com.jsoftware.recipes.data.repository

import android.util.Log
import com.jsoftware.recipes.data.remote.mealApi
import com.jsoftware.recipes.domain.models.CategoryList
import com.jsoftware.recipes.domain.models.Meal
import com.jsoftware.recipes.domain.models.PopularMeal
import com.jsoftware.recipes.domain.repository.MealRepository
import javax.inject.Inject

class MealRepoImplementation @Inject constructor(
    private val mealApi: mealApi
) : MealRepository {
    override suspend fun getRandomMeal(): Meal {
        return mealApi.getRandomMeal()
    }

    override suspend fun getMealCategories(): CategoryList {
        val response = mealApi.getMealCategories()
        Log.d("MealRepoImplementation", "API response: $response")
        return response
    }

    override suspend fun getMealById(id: String): Meal {
        return mealApi.getMealById(id)
    }

    override suspend fun getPopularMeal(): PopularMeal {
        return mealApi.getPopularMeal()
    }

    override suspend fun getMealsByCategory(category: String): PopularMeal {
        return mealApi.getMealsByCategory(category)
    }
}