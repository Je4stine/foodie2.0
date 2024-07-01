package com.jsoftware.recipes.presentation.categories

import com.jsoftware.recipes.domain.models.MealCategory

data class CategoryState(
    val isLoading: Boolean? = false,
    val category: List<MealCategory>? = emptyList()

)
