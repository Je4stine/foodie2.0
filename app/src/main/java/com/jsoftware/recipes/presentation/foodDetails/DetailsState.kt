package com.jsoftware.recipes.presentation.foodDetails

data class DetailsState(
    val meal: String? = "",
    val dateModified: Any? = "",
    val idMeal: String? = "",
    val strArea: String? = "",
    val strMeal: String? = "",
    val strMealThumb: String? = "",
    val isLoading: Boolean = false,
    val strInstructions: String? = "",
    val strCategory: String ? = ""
)