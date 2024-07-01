package com.jsoftware.recipes.domain.models

import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName("categories")
    val category: List<MealCategory>
)
