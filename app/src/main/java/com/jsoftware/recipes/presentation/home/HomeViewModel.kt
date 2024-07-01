package com.jsoftware.recipes.presentation.home

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
class HomeViewModel @Inject constructor(private val mealRepository: MealRepository) : ViewModel() {

    private val _state = MutableStateFlow(MealState())
    val state: StateFlow<MealState> = _state.asStateFlow()

    init {
        getRandomMeal()
    }

    private fun getRandomMeal() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val mealList = mealRepository.getRandomMeal()
                val meal = mealList.meals.firstOrNull()
                if (meal != null) {
                    _state.value = _state.value.copy(
                        meal = meal.strMeal,
                        dateModified = meal.dateModified,
                        idMeal = meal.idMeal,
                        strArea = meal.strArea,
                        strMeal = meal.strMeal,
                        strMealThumb = meal.strMealThumb,
                        isLoading = false
                    )
                } else {
                    _state.value = _state.value.copy(isLoading = false)
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false)

            }
        }
    }


}