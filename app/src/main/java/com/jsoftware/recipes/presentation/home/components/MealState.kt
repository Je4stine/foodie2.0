package com.jsoftware.recipes.presentation.home.components

data class MealState(
    val meal: String? = "",
    val dateModified: Any?  = "",
    val idMeal: String ? = "",
    val strArea: String ? = "",
    val strMeal: String? = "",
    val strMealThumb: String? = "",
    val isLoading: Boolean = false
)
