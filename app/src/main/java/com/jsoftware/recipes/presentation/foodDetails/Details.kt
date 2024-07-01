package com.jsoftware.recipes.presentation.foodDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import coil.compose.AsyncImage
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun FoodDetails(modifier: Modifier = Modifier, mealId: String?, navController: NavController) {

    val viewModel: DetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

                AsyncImage(
                    model = state.strMealThumb,
                    contentDescription = "Food Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = { navController.popBackStack()},
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        modifier = Modifier.size(25.dp)
                    )
                }
                Text(
                    text = state.strMeal?:"",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    ),

                    )

            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = state.strCategory?: "",  style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                Text(text = state.strArea?: "",  style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                IconButton(onClick = { }) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Magenta,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Add toFavourite",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "Instructions:",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = state.strInstructions?: "",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyMedium
            )

        }

    }
}