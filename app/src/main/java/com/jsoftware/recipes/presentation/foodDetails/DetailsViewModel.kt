package com.jsoftware.recipes.presentation.foodDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsoftware.recipes.domain.repository.MealRepository
import com.jsoftware.recipes.presentation.home.components.MealState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mealRepository: MealRepository
): ViewModel() {
    private  val mealId: String = checkNotNull(savedStateHandle["mealId"]);

    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()


    init {
        getMealById()
    }

    private fun getMealById (){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            try {
                val meal = mealRepository.getMealById(mealId)
                val deetsMeal = meal.meals.firstOrNull()

                if (deetsMeal != null){
                    _state.value = _state.value.copy(
                        idMeal = deetsMeal.idMeal,
                        strMeal = deetsMeal.strMeal,
                        strMealThumb = deetsMeal.strMealThumb,
                        isLoading = false,
                        strArea = deetsMeal.strArea,
                        strInstructions = deetsMeal.strInstructions,
                        strCategory = deetsMeal.strCategory
                    )
                }

            } catch (e: Exception){
                Log.d("Error", "{$e}")
                _state.value = _state.value.copy(
                    isLoading = true
                )
            }
        }
    }
}