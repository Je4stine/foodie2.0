package com.jsoftware.recipes.data.remote

import com.jsoftware.recipes.domain.models.CategoryList
import com.jsoftware.recipes.domain.models.Meal
import com.jsoftware.recipes.domain.models.MealCategory
import com.jsoftware.recipes.domain.models.MealList
import com.jsoftware.recipes.domain.models.PopularMeal
import retrofit2.http.GET
import retrofit2.http.Query

interface mealApi {
    @GET("/api/json/v1/1/random.php")
    suspend fun getRandomMeal(): Meal

    @GET("/api/json/v1/1/categories.php")
    suspend fun getMealCategories(): CategoryList

    @GET("/api/json/v1/1/lookup.php?")
    suspend fun getMealById(@Query("i") id: String): Meal

    @GET("/api/json/v1/1/filter.php?")
    suspend fun getPopularMeal(@Query("i") name: String = "chicken"): PopularMeal

    @GET("/api/json/v1/1/filter.php?")
    suspend fun getMealsByCategory(@Query("c") category: String): PopularMeal
}