package com.jsoftware.recipes.presentation.categoryDetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetails(modifier: Modifier = Modifier, mealId: String?, navController: NavController) {
    val detailsViewModel : CategoryDetailsViewModel = hiltViewModel()
    val catDetails by detailsViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Category Name",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                )
            })
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(catDetails.popularList!!.size) {index->
                val categoryDetail = catDetails.popularList!![index]
                CategoriesCard(img = categoryDetail.strMealThumb?:"", detailsClicked = {
                    navController.navigate("details/${categoryDetail.idMeal}")
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoriesCard(modifier: Modifier = Modifier, img: String, detailsClicked: ()->Unit) {
    Card(onClick = { detailsClicked()}, modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp)) {
        AsyncImage(
            model = img, contentDescription = "Food Image", modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}