package com.jsoftware.recipes.data.repository

import com.jsoftware.recipes.data.local.MealDao
import com.jsoftware.recipes.domain.models.Meal
import com.jsoftware.recipes.domain.models.MealList
import com.jsoftware.recipes.domain.repository.MealDbRepo
import javax.inject.Inject

class MealDbRepoImpl @Inject constructor(
    private val mealDao: MealDao
): MealDbRepo {
    override suspend fun insertMeal(meal: MealList) {
        mealDao.insertMeal(meal)
    }

    override suspend fun getAll(): List<MealList> {
        return mealDao.getAll()
    }
}