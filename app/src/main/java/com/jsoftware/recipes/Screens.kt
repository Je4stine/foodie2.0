package com.jsoftware.recipes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val title: String? = null, val icon: ImageVector? = null) {
    data object Home : Screens("home", "Home", Icons.Default.Home)
    data object Favourite : Screens("favourite", "Favourite", Icons.Default.Favorite)
    data object Categories : Screens("categories", "Categories", Icons.Default.List)
    data object Details : Screens("details/{mealId}")

//    data class Details(
//        val mealId: String
//    ): Screens("details/{$mealId}")
}

