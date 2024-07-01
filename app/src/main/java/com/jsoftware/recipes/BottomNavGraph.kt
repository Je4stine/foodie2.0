package com.jsoftware.recipes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jsoftware.recipes.presentation.categories.Categories
import com.jsoftware.recipes.presentation.categoryDetails.CategoryDetails
import com.jsoftware.recipes.presentation.favourite.Favourite
import com.jsoftware.recipes.presentation.foodDetails.FoodDetails
import com.jsoftware.recipes.presentation.home.Home

@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "bottom_navigation"
    ) {
        navigation(
            route = "bottom_navigation",
            startDestination = Screens.Home.route
        ){
            composable(Screens.Home.route) {
                Home( navController = navController)
            }
            composable(Screens.Favourite.route) {
                Favourite()
            }
            composable(Screens.Categories.route) {
                Categories( navController = navController)
            }
        }

        composable("details/{mealId}"){backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
            FoodDetails(navController = navController, mealId = mealId )
        }

        composable("categorydetails/{mealName}"){backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
//            FoodDetails(navController = navController, mealId = mealId )
            CategoryDetails( mealId = mealId,navController = navController )
        }
    }
}