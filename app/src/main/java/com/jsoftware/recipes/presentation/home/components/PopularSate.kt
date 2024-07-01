package com.jsoftware.recipes.presentation.home.components

import com.jsoftware.recipes.domain.models.Popular

data class PopularSate (
    val isLoading: Boolean? = false,
    val popularList: List<Popular>? = emptyList()
)