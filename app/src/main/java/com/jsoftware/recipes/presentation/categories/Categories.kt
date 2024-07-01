package com.jsoftware.recipes.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jsoftware.recipes.presentation.categoryDetails.CategoryDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: CategoryViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "All Categories",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                )
            })
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(it)
        ) {
            items(state.category!!.size) { index ->
                val category = state.category!![index]
                CategoryItem(
                    img = category.strCategoryThumb ?: "",
                    title = category.strCategory ?: "",
                    categoryClicked = {
                        navController.navigate("categorydetails/${category.strCategory}")
                    }
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    img: String,
    title: String,
    categoryClicked: ()->Unit
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable{
            categoryClicked()
        }
    ) {
        AsyncImage(
            model = img,
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
    }

}