package com.jsoftware.recipes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val NavigationItems = listOf(
        BottomItems("Home", Icons.Default.Home, Icons.Outlined.Home),
        BottomItems("Favourites", Icons.Default.Favorite, Icons.Outlined.Favorite),
        BottomItems("Categories", Icons.Default.List, Icons.Outlined.List)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            NavigationBar {
                NavigationItems.forEachIndexed { index, bottomItems ->
                    NavigationBarItem(selectedIndex == index,
                        label = {
                            Text(text = bottomItems.title)
                        },
                        onClick = {
                            selectedIndex = index
                            when (selectedIndex) {
                                0 -> navController.navigate(Screens.Home.route) {
                                    popUpTo(Screens.Home.route) { saveState = true }
                                    restoreState = true
                                    launchSingleTop = true
                                }

                                1 -> navController.navigate(Screens.Favourite.route) {
                                    popUpTo(Screens.Home.route) { saveState = true }
                                    restoreState = true
                                    launchSingleTop = true
                                }

                                2 -> navController.navigate(Screens.Categories.route) {
                                    popUpTo(Screens.Home.route) { saveState = true }
                                    restoreState = true
                                    launchSingleTop = true
                                }

                                else -> navController.navigate(Screens.Home.route)
                            }

                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedIndex == index) bottomItems.icon else bottomItems.unselectedIcon,
                                contentDescription = null
                            )
                        })
                }

            }

        }
    ) {
        val paddingValues = it
        BottomNavGraph(navController = navController, modifier = Modifier.padding(paddingValues))
    }
}

data class BottomItems(
    val title: String,
    val icon: ImageVector,
    val unselectedIcon: ImageVector
)