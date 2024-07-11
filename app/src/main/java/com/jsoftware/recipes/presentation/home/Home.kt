package com.jsoftware.recipes.presentation.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jsoftware.recipes.Screens
import com.jsoftware.recipes.presentation.categories.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier.fillMaxSize(),
    navController: NavHostController
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categoryState by categoryViewModel.state.collectAsState()

    val popularViewModel: PopularViewModel = hiltViewModel()
    val popularState by popularViewModel.state.collectAsState()



    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Hello there",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
            },

                actions = {
                    IconButton(onClick = { /* Handle filter action */ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                })
        }
    ) { paddingValues ->


        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            item {
                HeroSection(
                    onItemClick = {
                        navController.navigate("details/${state.idMeal}")
                    },
                    mealImg = state.strMealThumb ?: ""
                )
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Popular dishes")
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(popularState.popularList!!.size) { index ->
                            val popular = popularState.popularList!![index]
                            PopularItems(img = popular.strMealThumb ?: "", cardClicked = {
                                navController.navigate("details/${popular.idMeal}")
                            })
                        }

                    }
                }
            }
            item {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Categories")
                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier
                            .defaultMinSize(200.dp)
                            .height(250.dp)
                            .fillMaxWidth(1f)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.padding(20.dp)
                        ) {
                            items(categoryState.category!!.size) { index ->
                                val category = categoryState.category!![index]
                                CategoryItem(
                                    img = category.strCategoryThumb ?: "",
                                    title = category.strCategory ?: "",
                                    onCategoryClick = {
                                        navController.navigate("categorydetails/${category.strCategory}")
                                    }
                                )
                            }

                        }
                    }

                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroSection(
    onItemClick: () -> Unit,
    mealImg: String
) {
    Log.d("HeroSection", "Meal Image URL: $mealImg")
    Column(
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "What would you like to eat?")
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                onClick = onItemClick,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
            ) {
                Box {
                    AsyncImage(
                        model = mealImg,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }

            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PopularItems(
    img: String,
    cardClicked: () -> Unit
) {
    Card(
        onClick = cardClicked,
        modifier = Modifier
            .width(200.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(10.dp))
            .padding(end = 10.dp)
    ) {
        Box {
            AsyncImage(
                model = img,
                contentDescription = "Popular dishes",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

        }

    }
}


@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    img: String,
    title: String,
    onCategoryClick: ()->Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (
            modifier = Modifier.clickable {
                onCategoryClick()
            }
        ){
            AsyncImage(
                model = img,
                contentDescription = null,
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp),
                contentScale = ContentScale.Fit
            )
        }
        Text(text = title, style = MaterialTheme.typography.bodySmall)
    }
}